package iut.nantes.project.stores.service

import iut.nantes.project.stores.entity.ContactEntity
import iut.nantes.project.stores.repository.ContactRepository
import org.springframework.stereotype.Service

@Service
class ContactService(private val contactRepository: ContactRepository) {

    fun createContact(contactEntity: ContactEntity): ContactEntity {
        return contactRepository.save(contactEntity)
    }

    fun getContactById(id: Long): ContactEntity {
        return contactRepository.findById(id).orElseThrow { TODO() }
    }

    fun getContactsByCity(city: String): List<ContactEntity> {
        return contactRepository.findByCity(city)
    }
}
