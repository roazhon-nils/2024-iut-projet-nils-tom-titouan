package iut.nantes.project.stores.Exception


sealed class ContactException(message: String) : RuntimeException(message) {
    class InvalidDataException(message: String = "Invalid data") : ContactException(message)
    class ContactNotFoundException(message: String = "Contact could not be found") : ContactException(message)
    class ContactIsInAStoreException(message: String = "Contact cannot be deleted if it is in a store") : ContactException(message)
    class InvalidIdFormatException(message: String = "ID format is invalid") : ContactException(message)
}
