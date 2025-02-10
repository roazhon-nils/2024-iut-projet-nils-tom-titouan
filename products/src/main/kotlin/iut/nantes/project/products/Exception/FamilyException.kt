package iut.nantes.project.products.Exception


sealed class FamilyException(message: String) : RuntimeException(message) {
    class NameConflictException(message: String = "Family name already used") : FamilyException(message)
    class FamilyNotFoundException(message: String = "Family not found") : FamilyException(message)
    class InvalidIdFormatException(message: String = "Invalid ID format") : FamilyException(message)
}
