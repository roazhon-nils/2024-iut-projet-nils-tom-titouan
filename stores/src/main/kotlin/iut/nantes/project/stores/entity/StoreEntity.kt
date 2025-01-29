package iut.nantes.project.stores.entity

import iut.nantes.project.products.dto.ProductDto
import iut.nantes.project.products.entity.ProductEntity
import iut.nantes.project.stores.dto.StoreDto
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
data class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Size(min = 3, max = 30, message = "Name must have at least 3 characters and max 30 characters")
    val name: String,

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    val contact: ContactEntity,

    @OneToMany(mappedBy = "store", cascade = [CascadeType.ALL])
    val products: List<ProductEntity> = mutableListOf() /** Peut etre faire une autre classe pour Product **/
){
    fun toDto() : StoreDto {
        val productsD = mutableListOf<ProductDto>()
        for (product in products){
            productsD.add(product.toDto())
        }
        return StoreDto(id, name, contact, productsD)
    }
}