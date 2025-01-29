package iut.nantes.project.stores.entity

import ProductEntity
import jakarta.persistence.*
import jakarta.validation.constraints.Size

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