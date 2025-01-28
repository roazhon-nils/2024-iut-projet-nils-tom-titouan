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
    val name: String,
    val description: String
)

interface ProductJpa :
    JpaRepository<ProductEntity, UUID>

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String?,
    @Embedded
    val price: Price,
    @ManyToOne  (cascade = [CascadeType.ALL])
    val family: FamilyEntity
)

@Embeddable
@Table(name = "price")
data class Price(
    val amount: Double,
    val currency: String
)
