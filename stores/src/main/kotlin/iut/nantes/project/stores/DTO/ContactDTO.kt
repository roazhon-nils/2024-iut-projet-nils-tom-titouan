package iut.nantes.project.stores.DTO


import iut.nantes.project.stores.Entity.AddressEntity
import iut.nantes.project.stores.Entity.ContactEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ContactDTO(
    var id: Int? = null,

    @field:Email(message = "Email must be valid")
    var email: String,

    @field:Pattern(regexp = "^[0-9]{10}$", message = "Phone number must 10 numbers long")
    var phone: String,

    var address: AddressDTO
) {
    fun toEntity() = ContactEntity(id, email, phone, address.toEntity())

}

data class AddressDTO(

    @field:Size(min = 5, max = 50, message = "Street name must be 5 to 50 characters long")
    var street: String,

    @field:Size(min = 1, max = 30, message = "City name must be 1 to 30 characters long")
    var city: String,

    @field:Pattern(regexp = "^[0-9]{5}$", message = "Postal code must be of 5 numbers")
    var postalCode: String,


    ) {
    fun toEntity() = AddressEntity(street, city, postalCode)

}
