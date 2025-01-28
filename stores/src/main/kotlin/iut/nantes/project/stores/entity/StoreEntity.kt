package iut.nantes.project.stores.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.util.*

@Entity
data class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Size(min = 3, max = 30, message = "Store name must be between 3 and 30 characters")
    val name: String,

    @ManyToOne
    val contact: ContactEntity,

    @OneToMany
    val products: List<ProductEntity> = mutableListOf()
)

@Entity
data class ProductEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var quantity: Int
)