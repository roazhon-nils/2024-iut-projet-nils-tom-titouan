package iut.nantes.project.gateway.controller

import iut.nantes.project.gateway.dto.UserDto
import iut.nantes.project.gateway.service.UserService
import iut.nantes.project.products.dto.ProductDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("api/v1")
class GatewayController(private val userService: UserService) {
    private val webClient: WebClient = WebClient.create()

    @PostMapping("/user")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.createUser(userDto)
        return ResponseEntity.ok("User created successfully")
    }
    @GetMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    fun getProducts(@RequestHeader("X-User") user:String): ResponseEntity<List<ProductDto>> {
        val response = webClient.get().uri("http://products-service/api/v1/products")
            .header("X-User", user)
            .retrieve()
            .bodyToFlux(ProductDto::class.java)
            .collectList()
            .block()
        return ResponseEntity.ok(response)
    }
}