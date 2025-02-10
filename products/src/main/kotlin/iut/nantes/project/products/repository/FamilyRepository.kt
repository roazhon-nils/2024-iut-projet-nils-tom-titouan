package iut.nantes.project.products.Repository

import iut.nantes.project.products.FamilyEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FamilyRepository {
    fun save(family: FamilyEntity)
    fun findById(id: UUID): Optional<FamilyEntity>
    fun findAll(): List<FamilyEntity>
    fun existsByName(name: String): Boolean
    fun delete(family: FamilyEntity)
}

interface FamilyJpaRepository : JpaRepository<FamilyEntity, UUID> {
    fun existsByName(name: String): Boolean
}


class FamilyRepositoryJPA(private val familyJpaRepository: FamilyJpaRepository) : FamilyRepository {
    override fun save(family: FamilyEntity) {
        familyJpaRepository.save(family)
    }

    override fun findById(id: UUID): Optional<FamilyEntity> {
        return familyJpaRepository.findById(id)
    }

    override fun findAll(): List<FamilyEntity> {
        return familyJpaRepository.findAll()
    }

    override fun existsByName(name: String): Boolean {
        return familyJpaRepository.existsByName(name)
    }

    override fun delete(family: FamilyEntity) {
        familyJpaRepository.delete(family)
    }
}