package iut.nantes.project.stores.service

import iut.nantes.project.stores.entity.StoreEntity
import iut.nantes.project.stores.repository.ContactRepository
import iut.nantes.project.stores.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository,
                   private val contactRepository: ContactRepository) {
}
