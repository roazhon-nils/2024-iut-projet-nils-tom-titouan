package iut.nantes.project.stores.Controller

import iut.nantes.project.stores.DTO.ProductStoreDTO
import iut.nantes.project.stores.DTO.StoreDTO
import iut.nantes.project.stores.Service.StoreService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/stores")
class StoreController(private val storeService: StoreService) {

    @PostMapping
    fun createStore(storeDTO: StoreDTO): StoreDTO {
        return storeService.createStore(storeDTO)
    }

    @GetMapping
    fun getAllStores(): List<StoreDTO> {
        return storeService.getAllStores()
    }

    @GetMapping("/{id}")
    fun getStoreById(id: Int): StoreDTO {
        return storeService.getStoreById(id)
    }

    @PutMapping("/{id}")
    fun updateStore(id: Int, storeDTO: StoreDTO): StoreDTO {
        return storeService.updateStore(id, storeDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteStore(id: Int) {
        storeService.deleteStore(id)
    }

    @PostMapping("/{storeId}/products/{productId}/add")
    fun addProductToStore(
        storeId: Int,
        productId: UUID,
        quantity: Int
    ): ProductStoreDTO {
        return storeService.addProductToStore(storeId, productId, quantity)
    }

    @PostMapping("/{storeId}/products/{productId}/remove")
    fun removeProductFromStore(
        storeId: Int,
        productId: UUID,
        quantity: Int
    ): ProductStoreDTO {
        return storeService.removeProductFromStore(storeId, productId, quantity)
    }

    @DeleteMapping("/{storeId}/products")
    fun deleteProductsFromStore(
        storeId: Int,
        productIds: List<UUID>
    ) {
        storeService.deleteProductsFromStore(storeId, productIds)
    }


    @GetMapping("/products/{productId}/quantity")
    fun isProductOnStore(
        productId: UUID,
    ): Boolean {
        return storeService.isProductOnStore(productId)
    }
}
