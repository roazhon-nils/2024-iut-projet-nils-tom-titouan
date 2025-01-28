package iut.nantes.project.stores.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StockController {
    @PostMapping("/api/v1/stores/{storesId}/products/{productId}/add") //+filtre quantité
    fun addStock(@PathVariable storesId: String){
        TODO()
    }

    @PostMapping("/api/v1/stores/{storesId}/products/{productId}/remove") //+filtre quantité
    fun removeStock(@PathVariable storesId: String, @PathVariable productId: String){
        TODO()
    }

    @DeleteMapping("/api/v1/stores/{storesId}/products")
    fun deleteProducts(@PathVariable storesId: String, @PathVariable productId: String){
        TODO()
    }
}