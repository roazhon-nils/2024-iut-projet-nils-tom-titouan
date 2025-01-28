package iut.nantes.project.products.repository

import FamilyEntity
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FamilyRepositoryCustom {
    fun save(family: FamilyEntity)
    fun findById(id: UUID): Optional<FamilyEntity>
    fun findAll(): List<FamilyEntity>
    fun existsByName(name: String): Boolean
    fun delete(family: FamilyEntity)
}

interface FamilyJpaRepository : JpaRepository<FamilyEntity, UUID> {
    fun existsByName(name: String): Boolean
}

@Profile("!dev")
class FamilyRepositoryJPA(private val familyJpaRepository: FamilyJpaRepository) : FamilyRepositoryCustom {
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

@Profile("dev")
class FamilyRepositoryInMemory : FamilyRepositoryCustom {
    private val families = mutableMapOf<UUID, FamilyEntity>()

    override fun save(family: FamilyEntity) {
        families[family.id] = family
    }

    override fun findById(id: UUID): Optional<FamilyEntity> {
        return Optional.ofNullable(families[id])
    }

    override fun findAll(): List<FamilyEntity> {
        return families.values.toList()
    }

    override fun existsByName(name: String): Boolean {
        return families.values.any { it.name == name }
    }

    override fun delete(family: FamilyEntity) {
        families.remove(family.id)
    }
}
