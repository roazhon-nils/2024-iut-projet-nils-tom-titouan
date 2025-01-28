package iut.nantes.project.products.controller

import FamilyEntity
import iut.nantes.project.products.dto.FamilyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
class FamilleController(){

    @PostMapping("/api/v1/families")
    fun createfamily(@RequestBody family: FamilyDTO): ResponseEntity<FamilyDTO> {
            TODO()
//        val duplicate = db.findOne(family.name)
//        if (duplicate != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicate)
//        }
//        db.saveFamily(family).let {return ResponseEntity.status(HttpStatus.CREATED).body(it) }

    }

    @GetMapping("/api/v1/families")
    fun getFamilies(): ResponseEntity<List<FamilyDTO>> {
        TODO()
        //return ResponseEntity.statuts(HttpStatus.OK).body(db.getFamilies())
    }

    @GetMapping("/api/v1/families/{id}")
    fun getFamilyById(@PathVariable id: String): ResponseEntity<List<FamilyDTO>> {
        TODO()
    }

    @PutMapping("/api/v1/families/{id}")
    fun updateFamily(@PathVariable id: String, @RequestBody familyDto: FamilyDTO): ResponseEntity<FamilyDTO> {
        TODO()
    }

    @DeleteMapping("/api/v1/families/{id}")
    fun deleteFamily(@PathVariable id: String): ResponseEntity<Void> {
        TODO()
    }
}