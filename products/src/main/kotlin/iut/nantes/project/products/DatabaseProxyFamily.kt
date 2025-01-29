package iut.nantes.project.products

import iut.nantes.project.products.entity.FamilyEntity
import iut.nantes.project.products.dto.FamilyDTO

private fun FamilyEntity.toDto() = FamilyDTO(id, name, description)

private fun FamilyDTO.toEntity() = id?.let { FamilyEntity(it, name, description) }

class DatabaseProxyFamily {
    fun saveFamily(familyDto: FamilyDTO): FamilyDTO {
        val family = familyDto.toEntity()
        TODO()
    }
}