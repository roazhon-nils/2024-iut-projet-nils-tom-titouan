package iut.nantes.project.stores.dto

import iut.nantes.project.products.dto.ProductDto
import iut.nantes.project.products.entity.ProductEntity
import iut.nantes.project.stores.entity.ContactEntity
import iut.nantes.project.stores.entity.StoreEntity
import jakarta.validation.constraints.Size

data class StoreDto(
    val id: Long? = null,

    @field:Size(min = 3, max = 30, message = "Le nom doit contenir entre 3 et 30 caractères")
    val name: String,

    val contact: ContactDto,

    val products: List<ProductDto>
) {
    fun toEntity() : StoreEntity {
        val productsE = mutableListOf<ProductEntity>()
        for (product in products){
            productsE.add(product.toEntity())
        }
        return StoreEntity(id, name, contact.toEntity(), productsE)
    }
}