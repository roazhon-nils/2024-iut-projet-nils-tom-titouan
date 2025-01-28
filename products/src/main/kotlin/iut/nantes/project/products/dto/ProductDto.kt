package iut.nantes.project.products.dto

import PriceEntity
import ProductEntity
import java.util.*

data class ProductDto(
    val id: UUID?,
    val name: String,
    val description: String,
    val price: PriceDto,
    val famille: FamilyDTO
) {
    fun toEntity() = ProductEntity(
        id ?: UUID.randomUUID(),
        name,
        description,
        price.toEntity(),
        famille.toEntity()
    )
}

    data class PriceDto(val amount: Int, val currency: String) {
    fun toEntity() = PriceEntity(amount.toDouble(), currency)
}
