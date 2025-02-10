package iut.nantes.project.stores.Entity


import iut.nantes.project.stores.DTO.AddressDTO
import iut.nantes.project.stores.DTO.ContactDTO
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

@Entity
data class ContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @field:Email(message = "Invalid email")
    var email: String,

    @field:Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    var phone: String,

    @Embedded
    var address: AddressEntity
) {
    constructor() : this(null, "", "", AddressEntity("", "", ""))

    fun toDto() = ContactDTO(id, email, phone, address.toDto())
}


@Embeddable
data class AddressEntity(
    val street: String,
    val city: String,
    val postalCode: String
) {
    fun toDto() = AddressDTO(street, city, postalCode)
}
