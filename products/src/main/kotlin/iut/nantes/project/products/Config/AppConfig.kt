package iut.nantes.project.products.Config

import iut.nantes.project.products.ProductService
import iut.nantes.project.products.Repository.*
import iut.nantes.project.products.Service.FamilyService
import iut.nantes.project.products.Service.WebProductService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun familyRepositoryJPA(jpaRepository: FamilyJpaRepository): FamilyRepository {
        return FamilyRepositoryJPA(jpaRepository)
    }

    @Bean
    fun familyService(familyRepository: FamilyRepository): FamilyService {
        return FamilyService(familyRepository)
    }

    @Bean
    fun webProductService(): WebProductService {
        return WebProductService()
    }

    @Bean
    fun productRepositoryJPA(jpaRepository: ProductJpaRepository): ProductRepository {
        return ProductRepositoryJPA(jpaRepository)
    }

    @Bean
    fun productService(productRepository: ProductRepository, familyRepository: FamilyRepository, webProductService: WebProductService): ProductService {
        return ProductService(productRepository, familyRepository, webProductService)
    }
}
