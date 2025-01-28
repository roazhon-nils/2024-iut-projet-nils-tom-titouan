package iut.nantes.project.products.dto

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {
    @GetMapping("/api/v1/products")
    fun addProduct(@RequestBody product: ProductDto): ResponseEntity<ProductDto> {
        val withId = db.sa
    }
}