package iut.nantes.project.products.Exception

sealed class ProductException(message: String) : RuntimeException(message) {
    class ProductNotFoundException(message: String = "Product not found") : ProductException(message)
    class InvalidIdFormatException(message: String = "Invalid ID format") : ProductException(message)
    class ProductHasStockException(message: String = "Product still has stock") : ProductException(message)
}
