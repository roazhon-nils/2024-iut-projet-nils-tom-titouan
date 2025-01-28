package iut.nantes.project.products.repository

import ProductEntity
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepositoryCustom {
    fun save(productEntity: ProductEntity)
    fun findById(id: String): Optional<ProductEntity>
    fun findAll(): List<ProductEntity>
    fun existsByName(name: String): Boolean
    fun delete(id: UUID)
}

interface ProductJpaRepository : JpaRepository<ProductEntity, UUID> {
    fun existsByName(name: String): Boolean
}

@Profile("!dev")
class ProductRepositoryJPA(private val productJpaRepository: ProductJpaRepository) : ProductRepositoryCustom {
    override fun save(productEntity: ProductEntity) {
        productJpaRepository.save(productEntity)
    }

    override fun findById(id: String): Optional<ProductEntity> {
        return productJpaRepository.findById(UUID.fromString(id))
    }

    override fun findAll(): List<ProductEntity> {
        return productJpaRepository.findAll()
    }

    override fun existsByName(name: String): Boolean {
        return productJpaRepository.existsByName(name)
    }

    override fun delete(id: UUID) {
        productJpaRepository.deleteById(id)
    }
}

@Profile("dev")
class ProductRepositoryInMemory : ProductRepositoryCustom {
    private val products = mutableMapOf<UUID, ProductEntity>()
    override fun save(productEntity: ProductEntity) {
        products[productEntity.id] = productEntity
    }

    override fun findById(id: String): Optional<ProductEntity> {
        return try {
            Optional.ofNullable(products[UUID.fromString(id)])
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("L'identifiant fourni n'est pas un UUID valide")
        }
    }

    override fun findAll(): List<ProductEntity> {
        return products.values.toList()
    }

    override fun existsByName(name: String): Boolean {
        return products.values.any { it.name == name }
    }

    override fun delete(id: UUID) {
        products.remove(id)
    }
}