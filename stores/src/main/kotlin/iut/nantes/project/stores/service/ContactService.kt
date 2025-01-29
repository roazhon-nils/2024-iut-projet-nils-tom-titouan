package iut.nantes.project.stores.service

import iut.nantes.project.stores.dto.ContactDto
import iut.nantes.project.stores.entity.ContactEntity
import iut.nantes.project.stores.repository.ContactRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ContactService(private val contactRepository: ContactRepository) {

    fun createContact(contactDto: ContactDto): ContactDto {
        val contactEntity = ContactEntity(
            id = contactDto.id,
            email = contactDto.email,
            phone = contactDto.phone,
            address = contactDto.address.toEntity()
        )
        val newContact = contactRepository.save(contactEntity)
        return newContact.toDto()
    }

    fun getContactById(id: Long): ContactDto {
        val contactEntity = contactRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Contact non trouvé avec l'ID $id") }
        return contactEntity.toDto()
    }

    fun updateContact(id: Long, updatedContactDto: ContactDto): ContactDto {
        val existingContact = contactRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Contact non trouvé avec l'ID $id") }
        val updatedContactEntity = existingContact.copy(
            email = updatedContactDto.email,
            phone = updatedContactDto.phone,
            address = updatedContactDto.address.toEntity()
        )
        val savedUpdatedContact = contactRepository.save(updatedContactEntity)
        return savedUpdatedContact.toDto()
    }

    fun deleteContact(id: Long) {
        val contact = contactRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Contact non trouvé avec l'ID $id") }
        contactRepository.delete(contact)
    }

    fun getContactsByCity(city: String): List<ContactDto> {
        val contacts = contactRepository.findByAddressCity(city)
        return contacts.map { it.toDto() }
    }
}
