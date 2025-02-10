package iut.nantes.project.gateway.Service

import iut.nantes.project.gateway.Entity.UserEntity
import iut.nantes.project.gateway.Repository.UserRepository
import iut.nantes.project.gateway.dto.UserDTO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun createUser(userDTO: UserDTO): UserEntity {
        val encodedPassword = passwordEncoder.encode(userDTO.password)
        val userEntity = UserEntity(null, userDTO.login, encodedPassword)
        return userRepository.save(userEntity)
    }

    fun findByLogin(login: String): Optional<UserEntity> {
        return userRepository.findByLogin(login)
    }
}