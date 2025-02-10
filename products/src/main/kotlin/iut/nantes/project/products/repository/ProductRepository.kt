package iut.nantes.project.products.Repository

import iut.nantes.project.products.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository {
    fun save(product: ProductEntity)
    fun findById(id: String): Optional<ProductEntity>
    fun findAll(): List<ProductEntity>
    fun existsByName(name: String): Boolean
    fun delete(product: ProductEntity)
}

interface ProductJpaRepository : JpaRepository<ProductEntity, UUID> {
    fun existsByName(name: String): Boolean
}

class ProductRepositoryJPA(private val productJpaRepository: ProductJpaRepository) : ProductRepository {
    override fun save(product: ProductEntity) {
        productJpaRepository.save(product)
    }

    override fun findById(id: String): Optional<ProductEntity> {
        val uuid = UUID.fromString(id)
        return productJpaRepository.findById(uuid)
    }

    override fun findAll(): List<ProductEntity> {
        return productJpaRepository.findAll()
    }

    override fun existsByName(name: String): Boolean {
        return productJpaRepository.existsByName(name)
    }

    override fun delete(product: ProductEntity) {
        productJpaRepository.delete(product)
    }
}