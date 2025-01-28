package iut.nantes.project.products.dto

data class ProductDto(val id: Int?, val name: String, val description: String, val price: PriceDto, val famille: FamilleDto)

data class PriceDto(val amount: Int, val currency: String)