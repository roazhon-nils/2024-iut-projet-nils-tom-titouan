package iut.nantes.project.products.controller

import iut.nantes.project.products.dto.FamilyDTO
import iut.nantes.project.products.service.FamilyService
import iut.nantes.project.products.exception.FamilyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class FamilleController(
    private val familyService: FamilyService
) {

    @PostMapping("/families")
    fun createfamily(@RequestBody family: FamilyDTO): ResponseEntity<FamilyDTO> {
        return try {
            val createdFamily = familyService.createFamily(family)
            ResponseEntity.status(HttpStatus.CREATED).body(createdFamily)
        } catch (e: FamilyException.NameConflictException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } catch (e: FamilyException.InvalidDataException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @GetMapping("/families")
    fun getFamilies(): ResponseEntity<List<FamilyDTO>> {
        return try {
            val families = familyService.getAllFamilies()
            ResponseEntity.status(HttpStatus.OK).body(families)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/families/{id}")
    fun getFamilyById(@PathVariable id: UUID): ResponseEntity<FamilyDTO> {
        return try {
            val family = familyService.getFamilyById(id)
            ResponseEntity.status(HttpStatus.OK).body(family)
        } catch (e: FamilyException.InvalidIdFormatException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: FamilyException.FamilyNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PutMapping("/families/{id}")
    fun updateFamily(@PathVariable id: UUID, @RequestBody familyDto: FamilyDTO): ResponseEntity<FamilyDTO> {
        return try {
            val updatedFamily = familyService.updateFamily(id, familyDto)
            ResponseEntity.status(HttpStatus.OK).body(updatedFamily)
        } catch (e: FamilyException.InvalidDataException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: FamilyException.NameConflictException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @DeleteMapping("/families/{id}")
    fun deleteFamily(@PathVariable id: UUID): ResponseEntity<Void> {
        return try {
            familyService.deleteFamily(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: FamilyException.FamilyNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: FamilyException.FamilyHasProductsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}