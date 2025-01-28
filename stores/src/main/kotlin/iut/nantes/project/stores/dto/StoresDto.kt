package iut.nantes.project.stores.dto

data class StoresDto(val id: Int?, val name: String, val contact: ContactDto, val products: List<ProductDto>)

data class ProductDto(val id: Int?, val name: String, val quantity: Int)