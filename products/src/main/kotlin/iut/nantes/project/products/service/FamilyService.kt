package iut.nantes.project.products.service

import iut.nantes.project.products.entity.FamilyEntity
import iut.nantes.project.products.exception.FamilyException
import iut.nantes.project.products.dto.FamilyDTO
import iut.nantes.project.products.repository.FamilyRepositoryCustom
import org.springframework.stereotype.Service
import java.util.*

@Service
class FamilyService(private val familyRepository: FamilyRepositoryCustom) {
    fun createFamily(familyDto: FamilyDTO): FamilyDTO {
        if (familyRepository.existsByName(familyDto.name)) {
            throw FamilyException.NameConflictException()
        }
        val family = FamilyEntity(UUID.randomUUID(), familyDto.name, familyDto.description)
        familyRepository.save(family)
        return family.toDto()
    }

    fun getAllFamilies(): List<FamilyDTO> {
        return familyRepository.findAll().map { it.toDto() }
    }

    fun getFamilyById(id: UUID): FamilyDTO {
        return familyRepository.findById(id)
            .orElseThrow { FamilyException.FamilyNotFoundException() }.toDto()
    }

    fun updateFamily(id: UUID, familyDto: FamilyDTO): FamilyDTO {
        val family = familyRepository.findById(id).orElseThrow { FamilyException.FamilyNotFoundException() }
        if (familyRepository.existsByName(familyDto.name) && family.name != familyDto.name) {
            throw FamilyException.NameConflictException()
        }
        family.name = familyDto.name
        family.description = familyDto.description
        familyRepository.save(family)
        return family.toDto()
    }

    fun deleteFamily(id: UUID) {
        val family = familyRepository.findById(id).orElseThrow { FamilyException.FamilyNotFoundException() }
        familyRepository.delete(family)
    }
}