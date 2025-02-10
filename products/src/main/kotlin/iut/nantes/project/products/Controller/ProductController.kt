package iut.nantes.project.products.Controller

import iut.nantes.project.products.DTO.ProductDTO
import iut.nantes.project.products.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ProductController(private val productService: ProductService) {

    @PostMapping("/products")
    fun createProduct(productDto: ProductDTO): ResponseEntity<ProductDTO> {
        val createdProduct = productService.createProduct(productDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)

    }

    @GetMapping("/products")
    fun getAllProducts(
        familyName: String?,
        minPrice: Double?,
        maxPrice: Double?
    ): ResponseEntity<List<ProductDTO>> {
        val products = productService.getAllProducts(familyName, minPrice, maxPrice)
        return ResponseEntity.ok(products)
    }

    @GetMapping("/products/{id}")
    fun getProductById(id: UUID): ResponseEntity<ProductDTO> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @PutMapping("/products/{id}")
    fun updateProduct(id: UUID, productDto: ProductDTO): ResponseEntity<ProductDTO> {
        val updatedProduct = productService.updateProduct(id, productDto)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(id: UUID): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()

    }
}
