package iut.nantes.project.stores.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


@Entity
data class ContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @field:Email(message = "Email format is invalid")
    val email: String,

    @field:Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    val phone: String,

    @Embedded
    val address: AddressEntity
)

@Embeddable
data class AddressEntity(
    @field:Size(min = 5, max = 50, message = "Street must be between 5 and 50 characters")
    val street: String,

    @field:Size(min = 1, max = 30, message = "City must be between 1 and 30 characters")
    val city: String,

    @field:Pattern(regexp = "^[0-9]{5}$", message = "Postal code must be 5 digits")
    val postalCode: String
)
