package iut.nantes.project.stores.DTO

import iut.nantes.project.stores.Entity.StoreEntity
import jakarta.validation.constraints.Size

data class StoreDTO(
    var id: Int? = null,

    @field:Size(min = 3, max = 30, message = "The name must be 3 to 30 characters long")
    var name: String,

    var contact: ContactDTO,

    var products: List<ProductStoreDTO> = mutableListOf()
) {
    fun toEntity() = StoreEntity(id, name, contact.toEntity(), products.map { it.toEntity() }.toMutableList())
}
