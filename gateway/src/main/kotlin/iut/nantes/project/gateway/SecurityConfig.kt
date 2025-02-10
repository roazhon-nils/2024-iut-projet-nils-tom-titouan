import iut.nantes.project.gateway.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class SecurityConfig(
    @Autowired private val userRepository: UserRepository,
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { login: String ->
            val user = userRepository.findByLogin(login).orElseThrow {
                UsernameNotFoundException("User not found: $login")
            }
            org.springframework.security.core.userdetails.User(
                user.login,
                user.password,
                listOf(
                    SimpleGrantedAuthority("ROLE_USER")
                )
            )
        }
    }

}
