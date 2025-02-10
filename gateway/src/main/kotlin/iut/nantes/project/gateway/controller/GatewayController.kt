package iut.nantes.project.gateway.Controller

import iut.nantes.project.gateway.Service.UserService
import iut.nantes.project.gateway.dto.UserDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class GatewayController(private val userService: UserService) {

    @PostMapping("/user")
    fun createUser(userDTO: UserDTO): ResponseEntity<String> {
        userService.createUser(userDTO)
        return ResponseEntity.ok("Created a user")
    }

}
