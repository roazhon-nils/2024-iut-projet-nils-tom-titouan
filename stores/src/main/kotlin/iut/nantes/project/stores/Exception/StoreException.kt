package iut.nantes.project.stores.Exception

sealed class StoreException(message: String) : RuntimeException(message) {
    class InvalidDataException(message: String = "Invalid data") : StoreException(message)
    class StoreNotFoundException(message: String = "Store could not be found") : StoreException(message)
    class InvalidIdFormatException(message: String = "Invalid ID format") : StoreException(message)
    class StoreHasProductsException(message: String = "Store still has stock") : StoreException(message)
}
