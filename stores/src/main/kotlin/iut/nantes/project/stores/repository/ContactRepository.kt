package iut.nantes.project.stores.repository

import iut.nantes.project.stores.entity.ContactEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<ContactEntity, Long> {
    fun findByAddressCity(city: String): List<ContactEntity>
}