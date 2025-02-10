package iut.nantes.project.products

import iut.nantes.project.products.DTO.PriceDTO
import iut.nantes.project.products.DTO.ProductDTO
import jakarta.persistence.*
import java.util.*

@Entity
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String?,
    @Embedded
    var price: PriceEntity,
    @ManyToOne
    @JoinColumn(name = "family_id")
    var family: FamilyEntity
) {
    fun toDto() = ProductDTO(id, name, description, price.toDto(), family.toDto())
}

@Embeddable
data class PriceEntity(
    var amount: Double,
    var currency: String
) {
    fun toDto() = PriceDTO(amount, currency)
}
