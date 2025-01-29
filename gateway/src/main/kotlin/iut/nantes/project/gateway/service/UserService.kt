package iut.nantes.project.gateway.service

import iut.nantes.project.gateway.dto.UserDto
import iut.nantes.project.gateway.entity.UserEntity
import iut.nantes.project.gateway.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

class UserService(private val userRepository: UserRepository) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun createUser(userDto: UserDto): UserEntity {
        val encodedPassword = passwordEncoder.encode(userDto.password)
        val user = UserEntity(null, userDto.login, encodedPassword, userDto.isAdmin)
        return userRepository.save(user)
    }

    fun findByLogin(login: String): Optional<UserEntity> {
        return userRepository.findByLogin(login)
    }
}