package iut.nantes.project.stores.service

import iut.nantes.project.stores.dto.StoreDto
import iut.nantes.project.stores.entity.StoreEntity
import iut.nantes.project.stores.repository.ContactRepository
import iut.nantes.project.stores.repository.StoreRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val contactRepository: ContactRepository,
    private val webClient: WebClient
) {

    fun createStore(storeDto: StoreDto): StoreDto {
        val contactEntity = storeDto.contact.id?.let {
            contactRepository.findById(it)
                .orElseGet { contactRepository.save(storeDto.contact.toEntity()) }
        }

        val storeEntity = StoreEntity(
            name = storeDto.name,
            contact = contactEntity,
            products = mutableListOf()
        )

        val newStore = storeRepository.save(storeEntity)
        return newStore.toDto()
    }

    fun getAllStores(): List<StoreDto> {
        return storeRepository.findAll()
            .sortedBy { it.name }
            .map { it.toDto() }
    }

    fun getStoreById(id: Long): StoreDto {
        val store = storeRepository.findById(id).orElseThrow { StoreNotFoundException("Le magasin avec l'ID $id n'existe pas") }
        return store.toDto()
    }

    fun updateStore(id: Long, storeDto: StoreDto): StoreDto {
        val store = storeRepository.findById(id)
            .orElseThrow { StoreNotFoundException("Le magasin avec l'ID $id n'existe pas") }

        val contactEntity = contactRepository.findById(storeDto.contact.id)
            .orElseGet { contactRepository.save(storeDto.contact.toEntity()) }

        val updatedStore = store.copy(
            name = storeDto.name,
            contact = contactEntity
        )

        return storeRepository.save(updatedStore).toDto()
    }

    fun deleteStore(id: Long) {
        val store = storeRepository.findById(id)
            .orElseThrow { StoreNotFoundException("Le magasin avec l'ID $id n'existe pas") }

        storeRepository.delete(store)
    }

    fun addProductToStore(storeId: Long, productId: UUID, quantity: Int = 1): StoreDto {
        val store = storeRepository.findById(storeId)
            .orElseThrow { StoreNotFoundException("Le magasin avec l'ID $storeId n'existe pas") }

        val productExists = webClient.get()
            .uri("http://product-service/api/v1/products/$productId")
            .retrieve()
            .bodyToMono(Boolean::class.java)
            .block() ?: false

        if (!productExists) {
            throw ProductNotFoundException("Le produit avec l'ID $productId n'existe pas")
        }

        val updatedProducts = store.products.toMutableSet()
        val existingProduct = updatedProducts.find { it.id == productId }

        if (existingProduct != null) {
            existingProduct.quantity += quantity
        } else {
            updatedProducts.add(ProductEntity(id = productId, quantity = quantity))
        }

        val updatedStore = store.copy(products = updatedProducts)
        return storeRepository.save(updatedStore).toDto()
    }

    fun removeProductFromStore(storeId: Long, productId: UUID, quantity: Int = 1): StoreDto {
        val store = storeRepository.findById(storeId)
            .orElseThrow { StoreNotFoundException("Le magasin avec l'ID $storeId n'existe pas") }

        val updatedProducts = store.products.toMutableSet()
        val product = updatedProducts.find { it.id == productId }
            ?: throw ProductNotFoundException("Le produit avec l'ID $productId n'existe pas dans le magasin")

        if (product.quantity < quantity) {
            throw InsufficientStockException("Impossible de retirer $quantity unités, stock insuffisant")
        }

        product.quantity -= quantity
        if (product.quantity == 0) updatedProducts.remove(product)

        val updatedStore = store.copy(products = updatedProducts)
        return storeRepository.save(updatedStore).toDto()
    }

    fun deleteProductsFromStore(storeId: Long, productIds: List<UUID>) {
        val store = storeRepository.findById(storeId)
            .orElseThrow { StoreNotFoundException("Le magasin avec l'ID $storeId n'existe pas") }

        val updatedProducts = store.products.filterNot { it.id in productIds }.toSet()
        storeRepository.save(store.copy(products = updatedProducts))
    }
}
