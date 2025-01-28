package iut.nantes.project.products.repository

import ProductEntity
import iut.nantes.project.products.dto.ProductDto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepositoryCustom {
    fun save(productEntity: ProductEntity)
    fun findById(id: String): Optional<ProductEntity>
    fun findAll(): List<ProductEntity>
    fun existsByName(name: String): Boolean
    fun delete(product: ProductEntity)
}

interface ProductJpaRepository : JpaRepository<ProductEntity, UUID> {
    fun exists(name: String): Boolean
}