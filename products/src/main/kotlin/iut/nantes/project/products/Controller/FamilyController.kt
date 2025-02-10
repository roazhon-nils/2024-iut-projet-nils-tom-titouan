package iut.nantes.project.products.Controller

import iut.nantes.project.products.DTO.FamilyDTO
import iut.nantes.project.products.Service.FamilyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class FamilyController(private val familyService: FamilyService) {

    @PostMapping("/families")
    fun createFamily(familyDto: FamilyDTO): ResponseEntity<FamilyDTO> {
        val createdFamily = familyService.createFamily(familyDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFamily)
    }

    @GetMapping("/families")
    fun getAllFamilies(): ResponseEntity<List<FamilyDTO>> {
        val families = familyService.getAllFamilies()
        return ResponseEntity.ok(families)
    }

    @GetMapping("/families/{id}")
    fun getFamilyById(id: UUID): ResponseEntity<FamilyDTO> {
        val family = familyService.getFamilyById(id)
        return ResponseEntity.ok(family)
    }

    @PutMapping("/families/{id}")
    fun updateFamily(id: UUID, familyDto: FamilyDTO): ResponseEntity<FamilyDTO> {
        val updatedFamily = familyService.updateFamily(id, familyDto)
        return ResponseEntity.ok(updatedFamily)
    }

    @DeleteMapping("/families/{id}")
    fun deleteFamily(id: UUID): ResponseEntity<Void> {
        familyService.deleteFamily(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}
