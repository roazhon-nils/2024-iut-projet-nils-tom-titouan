package iut.nantes.project.gateway.config

import iut.nantes.project.gateway.repository.UserRepository
import iut.nantes.project.gateway.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun userService(userRepository: UserRepository) = UserService(userRepository)
}