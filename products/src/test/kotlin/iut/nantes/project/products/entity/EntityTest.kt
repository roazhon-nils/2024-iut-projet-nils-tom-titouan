package iut.nantes.project.products.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class EntityTest {

    @Nested
    inner class FamilyEntityTest {
        @Test
        fun `should create FamilyEntity with correct values`() {
            // Given
            val name = "Électronique"
            val description = "Produits électroniques"

            // When
            val family = FamilyEntity(name = name, description = description)

            // Then
            assertNotNull(family.id)
            assertEquals(name, family.name)
            assertEquals(description, family.description)
        }

        @Test
        fun `should convert FamilyEntity to DTO correctly`() {
            // Given
            val id = UUID.randomUUID()
            val name = "Électronique"
            val description = "Produits électroniques"
            val family = FamilyEntity(id = id, name = name, description = description)

            // When
            val dto = family.toDto()

            // Then
            assertEquals(id, dto.id)
            assertEquals(name, dto.name)
            assertEquals(description, dto.description)
        }
    }

    @Nested
    inner class PriceEntityTest {
        @Test
        fun `should create PriceEntity with correct values`() {
            // Given
            val amount = 99.99
            val currency = "EUR"

            // When
            val price = PriceEntity(amount = amount, currency = currency)

            // Then
            assertEquals(amount, price.amount)
            assertEquals(currency, price.currency)
        }

        @Test
        fun `should convert PriceEntity to DTO correctly`() {
            // Given
            val amount = 99.99
            val currency = "EUR"
            val price = PriceEntity(amount = amount, currency = currency)

            // When
            val dto = price.toDto()

            // Then
            assertEquals(99, dto.amount) // vérifie la conversion en Int
            assertEquals(currency, dto.currency)
        }
    }

    @Nested
    inner class ProductEntityTest {
        @Test
        fun `should create ProductEntity with correct values`() {
            // Given
            val name = "Smartphone"
            val description = "Un super téléphone"
            val price = PriceEntity(599.99, "EUR")
            val family = FamilyEntity(name = "Électronique", description = "Produits électroniques")

            // When
            val product = ProductEntity(
                name = name,
                description = description,
                price = price,
                family = family
            )

            // Then
            assertNotNull(product.id)
            assertEquals(name, product.name)
            assertEquals(description, product.description)
            assertEquals(price, product.price)
            assertEquals(family, product.family)
        }
    }
} 