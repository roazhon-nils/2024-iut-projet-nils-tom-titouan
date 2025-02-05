package iut.nantes.project.stores.controller

import iut.nantes.project.stores.dto.ContactDto
import iut.nantes.project.stores.service.ContactService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import iut.nantes.project.stores.dto.AddressDto

@ExtendWith(SpringExtension::class)
@WebMvcTest
class ContactControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var contactService: ContactService

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `should create a contact`() {
        val contactDto = ContactDto(
            id = 1,
            email = "test@email.com",
            phone = "0123456789",
            address = AddressDto("Rue truc", "Nantes", "44300")
        )

        Mockito.`when`(contactService.createContact(contactDto)).thenReturn(contactDto)

        mockMvc.perform(
            post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactDto))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.email").value(contactDto.email))
    }

    @Test
    fun `should return bad request when creating a contact with invalid data`() {
        val invalidContact = ContactDto(
            id = 1,
            email = "invalid-email",
            phone = "123",
            address = AddressDto("a", "", "1234")
        )

        mockMvc.perform(
            post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidContact))
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `should get a contact by id`() {
        val contactDto = ContactDto(
            id = 1,
            email = "test@email.com",
            phone = "0123456789",
            address = AddressDto("Rue truc", "Nantes", "44300")
        )

        Mockito.`when`(contactService.getContactById(1)).thenReturn(contactDto)

        mockMvc.perform(get("/api/v1/contacts/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(contactDto.id))
    }

    @Test
    fun `should return not found when contact does not exist`() {
        Mockito.`when`(contactService.getContactById(99)).thenReturn(null)

        mockMvc.perform(get("/api/v1/contacts/99"))
            .andExpect(status().isNotFound)
    }
}
