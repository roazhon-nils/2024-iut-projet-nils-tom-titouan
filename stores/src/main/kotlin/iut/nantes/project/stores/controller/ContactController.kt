package iut.nantes.project.stores.controller

import iut.nantes.project.stores.dto.ContactDto
import iut.nantes.project.stores.service.ContactService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/contacts")
class ContactController(private val contactService: ContactService) {
    @PostMapping
    fun createContact(@RequestBody contactDTO: ContactDto): ContactDto {
        return contactService.createContact(contactDTO)
    }

    @GetMapping
    fun getContactsByCity(@RequestParam city: String): List<ContactDto> {
        return contactService.getContactsByCity(city)
    }

    @GetMapping("/{id}")
    fun getContactById(@PathVariable id: Long) : ContactDto {
        return contactService.getContactById(id)
    }

    @PutMapping("/{id}")
    fun updateContact(
        @PathVariable id: Long,
        @RequestBody contactDTO: ContactDto
    ) : ContactDto {
        return contactService.updateContact(id, contactDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable id: Long) {
        contactService.deleteContact(id)
    }
}