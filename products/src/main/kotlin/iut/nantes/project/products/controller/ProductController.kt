package iut.nantes.project.products.controller

import iut.nantes.project.products.exception.FamilyException
import iut.nantes.project.products.dto.ProductDto
import iut.nantes.project.products.exception.ProductException
import iut.nantes.project.products.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1")

class ProductController(
    private val productService: ProductService
) {
    @PostMapping("/products")
    fun createProducts(@RequestBody product: ProductDto): ResponseEntity<ProductDto> {
        return try {
            val createdProduct = productService.createProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
        } catch (e: FamilyException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: ProductException.InvalidDataException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/products")
    fun getProducts(
        @RequestParam(required = false) family: String?,
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?
    ): ResponseEntity<List<ProductDto>> {
        return try {
            val products = productService.getAllProduct(family, minPrice, maxPrice)
            ResponseEntity.status(HttpStatus.OK).body(products)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable id: UUID): ResponseEntity<ProductDto> {
        return try {
            val product = productService.getProductById(id)
            ResponseEntity.ok(product)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @PutMapping("/products/{id}")
    fun updateProduct(@PathVariable id: UUID, @RequestBody product: ProductDto): ResponseEntity<ProductDto> {
        return try {
            val updatedProduct = productService.updateProduct(id, product)
            ResponseEntity.status(HttpStatus.OK).body(updatedProduct)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Void> {
        return try {
            productService.deleteProduct(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

}