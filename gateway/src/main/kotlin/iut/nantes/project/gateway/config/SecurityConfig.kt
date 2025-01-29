package iut.nantes.project.gateway.config

import iut.nantes.project.gateway.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val env: Environment
) {

    // Bean PasswordEncoder
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // Configuration de l'authentification
    @Autowired
    fun configureAuthentication(auth: AuthenticationManagerBuilder) {
        val securityMode = env.getProperty("gateway.security") ?: "database"

        if (securityMode == "inmemory") {
            // Utilisation de l'authentification en mémoire pour les tests
            auth.inMemoryAuthentication()
                .withUser("ADMIN")
                .password(passwordEncoder().encode("ADMIN"))
                .roles("ADMIN")
        } else {
            // Utilisation de l'authentification basée sur la base de données
            auth.userDetailsService(userDetailsService())
        }
    }

    // Bean UserDetailsService
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { login: String ->
            val user = userRepository.findByLogin(login).orElseThrow {
                UsernameNotFoundException("User not found: $login")
            }
            User(
                user.login,
                user.password,
                listOf(
                    if (user.isAdmin) SimpleGrantedAuthority("ROLE_ADMIN")
                    else SimpleGrantedAuthority("ROLE_USER")
                )
            )
        }
    }

    // Configuration de la sécurité HTTP
    @Throws(Exception::class)
    fun configureHttpSecurity(http: HttpSecurity) {
        http
            .authorizeRequests()
            .requestMatchers("/api/v1/user").permitAll() // Endpoint pour créer un utilisateur
            .requestMatchers("/**").hasRole("ADMIN") // Accès à tous les autres endpoints nécessite le rôle ADMIN
    }

}
