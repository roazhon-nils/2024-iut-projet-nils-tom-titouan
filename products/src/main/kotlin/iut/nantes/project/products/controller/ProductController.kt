package iut.nantes.project.products.controller

import iut.nantes.project.products.dto.ProductDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {
    @PostMapping("/api/v1/products")
    fun createProducts(@RequestBody product: ProductDto): ResponseEntity<ProductDto> {
        TODO()
    }

    @GetMapping("/api/v1/products") //+ les filtres à faire
    fun getProducts(): ResponseEntity<List<ProductDto>> {
        TODO()
        //return ResponseEntity.statuts(HttpStatus.OK).body(db.getProduct())
    }

    @GetMapping("/api/v1/products/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<List<ProductDto>> {
        TODO()
    }

    @PutMapping("/api/v1/products/{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody Product: ProductDto): ResponseEntity<ProductDto> {
        TODO()
    }

    @DeleteMapping("/api/v1/products/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<Void> {
        TODO()
    }

}