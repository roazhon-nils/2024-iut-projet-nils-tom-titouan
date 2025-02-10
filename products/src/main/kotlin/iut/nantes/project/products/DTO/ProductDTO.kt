package iut.nantes.project.products.DTO

import iut.nantes.project.products.PriceEntity
import iut.nantes.project.products.ProductEntity
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.util.*

data class ProductDTO(
    val id: UUID?,

    @field:Size(min = 2, max = 20, message = "Name must 5 to 100 characters long")
    val name: String,

    @field:Size(min = 5, max = 100, message = "Description must 5 to 100 characters long")
    val description: String?,

    val price: PriceDTO,

    val family: FamilyDTO
) {
    fun toEntity() = ProductEntity(id ?: UUID.randomUUID(), name, description, price.toEntity(), family.toEntity())

}

data class PriceDTO(
    @field:Positive(message = "Amount must a positive number")
    val amount: Double,
    @field:Pattern(
        regexp = "^[A-Z]{3}$",
        message = "Currency must be of 3 capital letters"
    )
    val currency: String
) {
    fun toEntity() = PriceEntity(amount, currency)

}