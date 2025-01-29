package iut.nantes.project.products.exception

sealed class FamilyException(message: String) : RuntimeException(message) {
    class InvalidDataException(message: String = "Les données fournies sont invalides.") : FamilyException(message)
    class NameConflictException(message: String = "Le nom de la famille est en conflit avec un nom déjà créer") :
        FamilyException(message)

    class FamilyNotFoundException(message: String = "La famille n'a pas été trouvée.") : FamilyException(message)
    class InvalidIdFormatException(message: String = "Le format de l'ID est invalide.") : FamilyException(message)
    class FamilyHasProductsException(message: String = "La famille contient encore des produits.") :
        FamilyException(message)
}