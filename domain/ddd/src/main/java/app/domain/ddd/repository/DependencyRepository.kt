package app.domain.ddd.repository

import app.domain.invoicing.dependency.Dependency
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DependencyRepository {

    private val dataSet: MutableList<Dependency> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize(){
        dataSet.add(
            Dependency(
                id = "1",
                name = "Dependencia 1",
                shortName = "D1",
                description = "Descripción de la dependencia 1",
                imageUrl = ""
            )
        )
    }

    suspend fun getDependency(): Flow<List<Dependency>> {
        delay(1000)

        return flow { emit(dataSet) }
    }

    suspend fun existDependency(name: String): Boolean {
        delay(1000)
        return dataSet.any {it.name == name }
    }

    suspend fun createDependency(
        id: String,
        name: String,
        shortName: String,
        description: String,
        imageUrl: String

        ): Boolean {
        delay(2000)
        return dataSet.add(
            Dependency(
                id,
                name,
                shortName,
                description,
                imageUrl
            )
        )
    }

    suspend fun updateDependency(
        id: String,
        name: String,
        shortName: String,
        description: String,
        imageUrl: String
        ): Boolean {
        delay(2000)
        val index = dataSet.indexOfFirst { it.id == id }
        return if (index != -1) {
            dataSet[index] = Dependency(
                id,
                name,
                shortName,
                description,
                imageUrl
            )
            true
        }else{
            false
        }
    }

    suspend fun deleteDependency(dependency: Dependency): Boolean {
        delay(2000)
        return dataSet.remove(dependency)
    }
}