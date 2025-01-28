import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "familly")
data class Family(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String
)

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String?,
    val price: Price,
    val family: Family
)

@Entity
@Table(name = "price")
data class Price(
    val amount: Double,
    val currency: String
)
