package iut.nantes.project.stores.Controller

import iut.nantes.project.stores.DTO.ContactDTO
import iut.nantes.project.stores.Service.ContactService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/contacts")
class ContactController(private val contactService: ContactService) {

    @PostMapping
    fun createContact(contact: ContactDTO): ContactDTO {
        return contactService.createContact(contact)
    }

    @GetMapping
    fun getContactsByCity(city: String): List<ContactDTO> {
        return contactService.getContactsByCity(city)
    }


    @GetMapping("/{id}")
    fun getContactById(id: Int): ContactDTO? {
        return contactService.getContactById(id)
    }

    @PutMapping("/{id}")
    fun updateContact(id: Int, contactEntity: ContactDTO): ContactDTO? {
        return contactService.updateContact(id, contactEntity)
    }

    @DeleteMapping("/{id}")
    fun deleteContact(id: Int) {
        return contactService.deleteContact(id)

    }
}
