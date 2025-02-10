package iut.nantes.project.stores.Entity

import iut.nantes.project.stores.DTO.ProductStoreDTO
import jakarta.persistence.*
import java.util.UUID

@Entity
data class ProductStoreEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    val name: String,

    var quantity: Int,

    @ManyToOne
    @JoinColumn(name = "store_id")
    val store: StoreEntity? = null
) {
    fun toDTO() = ProductStoreDTO(id, name, quantity)
}

