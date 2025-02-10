package iut.nantes.project.stores.Entity

import iut.nantes.project.stores.DTO.StoreDTO
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
data class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @field:Size(min = 3, max = 30, message = "The name must be 3 to 30 characters long")
    var name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    var contact: ContactEntity,

    @OneToMany(mappedBy = "store", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: MutableList<ProductStoreEntity> = mutableListOf()
) {
    fun toDto() = StoreDTO(id, name, contact.toDto(), products.map { it.toDTO() }.toList())
}
