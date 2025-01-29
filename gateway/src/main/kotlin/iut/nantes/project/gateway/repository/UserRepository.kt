package iut.nantes.project.gateway.repository

import iut.nantes.project.gateway.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional


interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByLogin(login: String): Optional<UserEntity>
}