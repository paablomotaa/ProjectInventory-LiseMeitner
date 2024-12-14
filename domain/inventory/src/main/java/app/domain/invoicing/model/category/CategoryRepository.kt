package app.domain.invoicing.model.category

import app.base.utils.BaseResult
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

object CategoryRepository {

    private var dataSet: MutableList<Category> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        if (dataSet.isEmpty()) {
            dataSet.add(Category(
                id = "1",
                name = "Bebidas",
                shortName = "BEV",
                description = "Productos líquidos para consumo",
                imageUrl = "https://example.com/beverages.jpg",
                createdDate = Date(),
                type = CategoryType.BASICO,
                isFungible = true
            ))

            dataSet.add(Category(
                id = "2",
                name = "Snacks",
                shortName = "SNK",
                description = "Comida ligera y rápida",
                imageUrl = "https://example.com/snacks.jpg",
                createdDate = Date(),
                type = CategoryType.ECONOMICO,
                isFungible = false
            ))
        }
    }

    suspend fun getData(): Flow<List<Category>> {
        delay(2000)  // Simula un retraso en la obtención de los datos
        return flow { emit(dataSet) }
    }

    suspend fun addCategory(category: Category) {
        dataSet.add(category)
    }

    suspend fun delete(category: Category) {
        dataSet.remove(category)
    }

    suspend fun edit(
        id: String,
        name: String,
        shortName: String,
        description: String,
        imageUrl: String,
        type: CategoryType,
        isFungible: Boolean
    ) {
        val category = dataSet.firstOrNull { it.id == id }
        category?.let {
            it.name = name
            it.shortName = shortName
            it.description = description
            it.imageUrl = imageUrl
            it.type = type
            it.isFungible = isFungible
        }
    }

    suspend fun isDuplicate(id: String): BaseResult<Category> {
        val category = dataSet.firstOrNull { it.id == id }
        return if (category != null) {
            BaseResult.Success(category)
        } else {
            BaseResult.Error(CategoryException.DuplicateCategory)
        }
    }
}