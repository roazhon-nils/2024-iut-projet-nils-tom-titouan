package iut.nantes.project.stores.controller

import iut.nantes.project.stores.dto.ContactDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ContactController {
    @PostMapping("/api/v1/contacts")
    fun createContact(@RequestBody contact: ContactDto): ResponseEntity<ContactDto> {
        TODO()
    }

    @GetMapping("/api/v1/contacts") // + filtre
    fun getAllContacts(@PathVariable id: String): ResponseEntity<List<ContactDto>> {
        TODO()
    }

    @GetMapping("/api/v1/contacts/{id}")
    fun getContactById(@PathVariable id: String): ResponseEntity<ContactDto> {
        TODO()
    }

    @PutMapping("/api/v1/contacts/{id}")
    fun updateContact(@PathVariable id: String, @RequestBody contact: ContactDto): ResponseEntity<ContactDto> {
        TODO()
    }

    @DeleteMapping("/api/v1/contacts/{id}")
    fun deleteContactById(@PathVariable id: String): ResponseEntity<Void> {
        TODO()
    }

}