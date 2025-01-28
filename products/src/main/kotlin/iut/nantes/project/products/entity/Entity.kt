import iut.nantes.project.products.dto.FamilyDTO
import iut.nantes.project.products.dto.PriceDto
import iut.nantes.project.products.dto.ProductDto
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FamilyJpa :
    JpaRepository<FamilyEntity, UUID>

@Entity
@Table(name = "family")
data class FamilyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String
) {
    fun toDto() = FamilyDTO(id, name, description)
}

interface ProductJpa :
    JpaRepository<ProductEntity, UUID>

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String,
    @Embedded
    var price: PriceEntity,
    @ManyToOne  (cascade = [CascadeType.ALL])
    var family: FamilyEntity
)  {
    fun toDto() = ProductDto(id, name, description, price.toDto(), family.toDto())
}

@Embeddable
@Table(name = "price")
data class PriceEntity(
    val amount: Double,
    val currency: String
) {
    fun toDto() = PriceDto(amount.toInt(), currency)
}
