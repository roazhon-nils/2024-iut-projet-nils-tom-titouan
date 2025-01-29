package iut.nantes.project.products.service

import iut.nantes.project.products.entity.ProductEntity
import iut.nantes.project.products.exception.FamilyException
import iut.nantes.project.products.dto.ProductDto
import iut.nantes.project.products.exception.ProductException
import iut.nantes.project.products.repository.FamilyRepositoryCustom
import iut.nantes.project.products.repository.ProductRepositoryCustom
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val familyRepository: FamilyRepositoryCustom,
    private val productRepository: ProductRepositoryCustom
) {
    fun createProduct(productDto: ProductDto): ProductDto {
        val familyId = productDto.id ?: throw FamilyException.InvalidIdFormatException()
        val family = familyRepository.findById(familyId)
            .orElseThrow { FamilyException.FamilyNotFoundException() }

        val product = ProductEntity(
            UUID.randomUUID(),
            productDto.name,
            productDto.description,
            productDto.price.toEntity(),
            family
        )
        productRepository.save(product)
        return product.toDto()
    }

    fun getAllProduct(familyName: String?, minPrice: Double?, maxPrice: Double?): List<ProductDto> {
        val products = productRepository.findAll()  
        return products
            .filter { product ->
                (familyName == null || product.family.name == familyName) &&
                (minPrice == null || product.price.amount >= minPrice) &&
                (maxPrice == null || product.price.amount <= maxPrice)
            }
            .map { it.toDto() }
    }

    fun getProductById(id: UUID): ProductDto {
        return productRepository.findById(id.toString())
            .orElseThrow { ProductException.ProductNotFoundException() }.toDto()
    }

    fun updateProduct(id: UUID, productDto: ProductDto): ProductDto {
        val product = productRepository.findById(id.toString())
            .orElseThrow { ProductException.ProductNotFoundException() }

        val familyId = productDto.famille.id ?: throw FamilyException.InvalidIdFormatException()
        val family = familyRepository.findById(familyId)
            .orElseThrow { FamilyException.FamilyNotFoundException() }

        product.name = productDto.name
        product.description = productDto.description
        product.price = productDto.price.toEntity()
        product.family = family

        productRepository.save(product)
        return product.toDto()
    }

    fun deleteProduct(id: UUID) {
        productRepository.delete(id)
    }
}