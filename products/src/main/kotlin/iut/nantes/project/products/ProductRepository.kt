package iut.nantes.project.products

import iut.nantes.project.products.dto.ProductDto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductDto, Int> {
}