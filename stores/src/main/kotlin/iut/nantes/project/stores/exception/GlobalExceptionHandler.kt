package iut.nantes.project.stores.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFoundException(ex: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequestException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne est survenue.")
    }
}
