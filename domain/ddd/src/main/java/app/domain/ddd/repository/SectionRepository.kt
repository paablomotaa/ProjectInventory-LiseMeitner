package app.domain.ddd.repository

import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.section.Section
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

object SectionRepository {

    private val dataSet: MutableList<Section> = mutableListOf()

    init {
        initialize()
    }

    fun initialize(){
        dataSet.add(
            Section(
                id = "1",
                name = "Sección 1",
                shortName = "S1",
                description = "Descripción de la sección 1",
                dependency = Dependency(
                    id = "1",
                    name = "Dependencia 1",
                    shortName = "D1",
                    description = "Descripción de la dependencia 1",
                    imageUrl = ""),
                imageUrl = "",
                createdDate = Date()
            )
        )
    }

    suspend fun getSections(): Flow<List<Section>> {
        delay(1000)

        return flow { emit(dataSet) }
    }

    suspend fun existSection(name: String): Boolean {
        delay(1000)
        return dataSet.any {it.name == name }
    }

    suspend fun createSection(
        id: String,
        name: String,
        shortName: String,
        description: String,
        dependency: Dependency,
        imageUrl: String,
        createdDate: Date
    ): Boolean {
        delay(2000)
        return dataSet.add(
            Section(
                id,
                name,
                shortName,
                description,
                dependency,
                imageUrl,
                createdDate
            )
        )
    }

    suspend fun updateSection(
        id: String,
        name: String,
        shortName: String,
        description: String,
        dependency: Dependency,
        imageUrl: String,
        createdDate: Date
    ): Boolean {
        delay(2000)
        val index = dataSet.indexOfFirst { it.id == id }
        return if (index != -1) {
            dataSet[index] = Section(
                id,
                name,
                shortName,
                description,
                dependency,
                imageUrl,
                createdDate
            )
            true
        }else{
            false
        }
    }

    suspend fun deleteSection(section: Section): Boolean {
        delay(2000)
        return dataSet.remove(section)
    }
}