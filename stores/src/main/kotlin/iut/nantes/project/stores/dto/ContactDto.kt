package iut.nantes.project.stores.dto

import iut.nantes.project.stores.entity.AddressEntity
import iut.nantes.project.stores.entity.ContactEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ContactDto(
    val id: Long? = null,

    @field:Email(message = "Format d'email invalide")
    val email: String,

    @field:Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    val phone: String,

    val address: AddressDto
) {
    fun toEntity() = ContactEntity(id, email, phone, address.toEntity())
}

data class AddressDto(
    @field:Size(min = 5, max = 50, message = "La rue doit faire entre 5 et 50 caractères")
    val street: String,

    @field:Size(min = 1, max = 30, message = "La ville doit faire entre 1 et 30 caractères")
    val city: String,

    @field:Pattern(regexp = "\\d{5}", message = "Le code postal doit être un nombre à 5 chiffres")
    val postalCode: String
) {
    fun toEntity() = AddressEntity(street, city, postalCode)
}