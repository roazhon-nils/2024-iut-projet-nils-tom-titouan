package iut.nantes.project.stores.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

data class StoresEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @field:Size(min = 1, max = 10, message = "TODO")
    var name: String,
){
}