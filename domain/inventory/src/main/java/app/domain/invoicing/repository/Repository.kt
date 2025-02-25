package app.domain.invoicing.repository

import app.domain.invoicing.model.UniqueId


interface Repository<ID : UniqueId, E> {


    suspend fun save(entity: E) {
        TODO()
    }

    suspend fun remove(entity: E) {
        TODO()
    }

}
