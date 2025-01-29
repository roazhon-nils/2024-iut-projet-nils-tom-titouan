package iut.nantes.project.stores.dto

import iut.nantes.project.products.dto.ProductDto
import iut.nantes.project.products.entity.ProductEntity
import iut.nantes.project.stores.entity.AddressEntity
import iut.nantes.project.stores.entity.ContactEntity
import iut.nantes.project.stores.entity.StoreEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class StoreDto(
    val id: Long? = null,

    val name: String,

    val contact: ContactEntity,

    val products: List<ProductDto>
) {
    var productsE = mutableListOf<ProductEntity>()

    fun toEntity() : StoreEntity {
        val productsE = mutableListOf<ProductEntity>()
        for (product in products){
            productsE.add(product.toEntity())
        }
        return StoreEntity(id, name, contact, productsE)
    }
}