package iut.nantes.project.products.dto

import jakarta.validation.constraints.Size

data class FamilleDto(
    val id: Int?,
    @field:Size(min = 3, max = 30, message = "Le nom de la famille doit faire entre 3 et 30 caractères.")
    val name: String,
    @field:Size(min = 3, max = 100, message = "Le nom de la famille doit faire entre 3 et 30 caractères.")
    val description: String
)