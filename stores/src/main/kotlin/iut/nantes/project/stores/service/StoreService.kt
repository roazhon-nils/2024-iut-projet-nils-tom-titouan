package iut.nantes.project.stores.service

import iut.nantes.project.stores.dto.StoreDto
import iut.nantes.project.stores.entity.StoreEntity
import iut.nantes.project.stores.repository.ContactRepository
import iut.nantes.project.stores.repository.StoreRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val contactRepository: ContactRepository
) {


    @Transactional
    fun createStore(storeDto: StoreDto): StoreDto {
        val contactEntity = contactRepository.findById(storeDto.contact.id!!)
            .orElseGet { contactRepository.save(storeDto.contact.toEntity()) }

        val store = StoreEntity(
            name = storeDto.name,
            contact = contactEntity,
            products = mutableListOf()
        )

        val savedStore = storeRepository.save(store)
        return savedStore.toDto()
    }


    fun getAllStores(): List<StoreDto> {
        return storeRepository.findAll()
            .sortedBy { it.name }
            .map { it.toDto() }
    }


    fun getStoreById(id: Long): StoreDto {
        require(id > 0) { throw IllegalArgumentException("ID invalide") }
        val store = storeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Magasin non trouvé pour l'ID: $id") }
        return store.toDto()
    }



    @Transactional
    fun updateStore(id: Long, storeDto: StoreDto): StoreDto {
        require(id > 0) { "ID invalide" }

        val storeEntity = storeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Magasin non trouvé") }

        val contactEntity = contactRepository.findById(storeDto.contact.id!!)
            .orElseGet { contactRepository.save(storeDto.contact.toEntity()) }

        storeEntity.name = storeDto.name
        storeEntity.contact = contactEntity
        val updatedStore = storeRepository.save(storeEntity)
        return updatedStore.toDto()
    }


    @Transactional
    fun deleteStore(id: Long) {
        require(id > 0) { "ID invalide" }

        val store = storeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Magasin non trouvé") }

        storeRepository.delete(store)
    }
}
