package iut.nantes.project.stores.controller

import iut.nantes.project.stores.dto.StoresDto
import iut.nantes.project.stores.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StoreController(private val storeService: StoreService){

    @PostMapping("/api/v1/stores")
    fun createStore(@RequestBody store: StoresDto): ResponseEntity<StoresDto> {
        TODO()
    }

    @GetMapping("/api/v1/stores")
    fun getAllStores(@PathVariable id: String): ResponseEntity<List<StoresDto>> {
        TODO()
    }

    @GetMapping("/api/v1/stores/{id}")
    fun getStoreById(@PathVariable id: String): ResponseEntity<StoresDto> {
        TODO()
    }

    @PutMapping("/api/v1/stores/{id}")
    fun updateStore(@PathVariable id: String, @RequestBody store: StoresDto): ResponseEntity<StoresDto> {
        TODO()
    }

    @DeleteMapping("/api/v1/stores/{id}")
    fun deleteStore(@PathVariable id: String): ResponseEntity<Void> {
        TODO()
    }
}