package iut.nantes.project.stores.repository

import iut.nantes.project.stores.dto.StoresDto
import iut.nantes.project.stores.entity.StoresEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoresRepository : JpaRepository<StoresEntity, Int> {
    fun find(): StoresDto
}