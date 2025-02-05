package iut.nantes.project.stores.controller

import iut.nantes.project.stores.dto.StoreDto
import iut.nantes.project.stores.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/stores")
class StoreController(private val storeService: StoreService) {

    @PostMapping
    fun createStore(@RequestBody store: StoreDto): ResponseEntity<StoreDto> {
        val createdStore = storeService.createStore(store)
        return ResponseEntity.ok(createdStore)
    }

    @GetMapping
    fun getAllStores(): ResponseEntity<List<StoreDto>> {
        val stores = storeService.getAllStores()
        return ResponseEntity.ok(stores)
    }

    @GetMapping("/{id}")
    fun getStoreById(@PathVariable id: Long): ResponseEntity<StoreDto> {
        val store = storeService.getStoreById(id)
        return ResponseEntity.ok(store)
    }

    @PutMapping("/{id}")
    fun updateStore(@PathVariable id: Long, @RequestBody store: StoreDto): ResponseEntity<StoreDto> {
        val updatedStore = storeService.updateStore(id, store)
        return ResponseEntity.ok(updatedStore)
    }

    @DeleteMapping("/{id}")
    fun deleteStore(@PathVariable id: Long): ResponseEntity<Void> {
        storeService.deleteStore(id)
        return ResponseEntity.noContent().build()
    }
}