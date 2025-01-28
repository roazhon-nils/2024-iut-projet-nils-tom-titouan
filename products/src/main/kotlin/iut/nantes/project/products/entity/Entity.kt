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
    @ManyToOne(cascade = [CascadeType.ALL])
    val price: Price,
    @ManyToOne  (cascade = [CascadeType.ALL])
    val family: Family
)

@Entity
@Table(name = "price")
data class Price(
    @Id
    val amount: Double,
    val currency: String
)
