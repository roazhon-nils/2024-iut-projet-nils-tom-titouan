package iut.nantes.project.stores.errors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorsHandler {

    @ExceptionHandler(ContactNotFoundException::class)
    fun handleContactNotFoundException(ex: ContactNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found")
    }

    @ExceptionHandler(StoreNotFoundException::class)
    fun handleStoreNotFoundException(ex: StoreNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errorMessages = ex.bindingResult.allErrors.map { it.defaultMessage }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages)
    }
}
