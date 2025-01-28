package iut.nantes.project.stores.Controller

data class ContactDto(val id: Int?, val email: String, val phone: String, val address: AddressDto)

data class AddressDto(val street: String, val city: String, val postalCode: String)