package iut.nantes.project.products

import FamilyEntity
import FamilyJpa
import iut.nantes.project.products.dto.FamilyDTO
import iut.nantes.project.products.dto.ProductDto

private fun FamilyEntity.toDto() = FamilyDTO(id, name, description)

private fun FamilyDTO.toEntity() = id?.let { FamilyEntity(it, name, description) }

class DatabaseProxyFamily {
    fun saveFamily(familyDto: FamilyDTO): FamilyDTO {
        val family = familyDto.toEntity()
        TODO()
    }
}