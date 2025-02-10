package iut.nantes.project.products.DTO

import iut.nantes.project.products.FamilyEntity
import jakarta.validation.constraints.Size
import java.util.*

data class FamilyDTO(
    val id: UUID?,

    @field:Size(min = 3, max = 30, message = "Name must be 3 to 30 characters long")
    val name: String,

    @field:Size(min = 5, max = 100, message = "Description must be 5 to 100 characters long")
    val description: String
) {
    fun toEntity() = FamilyEntity(id ?: UUID.randomUUID(), name, description)
}