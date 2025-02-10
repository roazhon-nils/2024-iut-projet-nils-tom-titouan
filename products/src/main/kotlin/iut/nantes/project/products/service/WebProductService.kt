package iut.nantes.project.products.Service

import org.springframework.web.reactive.function.client.WebClient
import java.util.*

class WebProductService {

    private val storeWebClient: WebClient = WebClient.create("http://localhost:8082")

    fun isProductOnStore(productId: UUID): Boolean {
        return storeWebClient.get()
            .uri("/api/v1/stores/products/$productId/quantity")
            .retrieve()
            .bodyToMono(Boolean::class.java)
            .block() ?: false
    }
}