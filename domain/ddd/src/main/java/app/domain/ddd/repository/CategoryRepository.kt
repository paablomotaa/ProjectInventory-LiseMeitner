package app.domain.ddd.repository

import app.base.utils.BaseResult
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

object CategoryRepository {

    private val dataSet: MutableList<Category> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        if (dataSet.isEmpty()) {
            dataSet.addAll(
                listOf(
                    Category(
                        id = "1",
                        name = "Bebidas",
                        shortName = "BEV",
                        description = "Productos líquidos para consumo",
                        imageUrl = "https://example.com/beverages.jpg",
                        createdDate = Date(),
                        type = CategoryType.BASICO,
                        isFungible = true
                    ),
                    Category(
                        id = "2",
                        name = "Snacks",
                        shortName = "SNK",
                        description = "Comida ligera y rápida",
                        imageUrl = "https://example.com/snacks.jpg",
                        createdDate = Date(),
                        type = CategoryType.ECONOMICO,
                        isFungible = false
                    )
                )
            )
        }
    }

    fun getAllCategories(): Flow<List<Category>> = flow {
        delay(2000)
        emit(dataSet)
    }

    suspend fun addCategory(category: Category): BaseResult<Unit> =
        try {
            dataSet.add(category)
            BaseResult.Success(Unit)
        } catch (e: Exception) {
            BaseResult.Error(e)
        }

    suspend fun isDuplicate(name: String): BaseResult<Boolean> {
        return runCatching {
            val exists = dataSet.any { it.name.equals(name, ignoreCase = true) }
            exists
        }.fold(
            onSuccess = { BaseResult.Success(it) },
            onFailure = { BaseResult.Error(it as Exception) }
        )
    }

    suspend fun deleteCategory(category: Category) {
        dataSet.remove(category)
    }


    fun categoryExists(category: Category): Boolean =
        dataSet.any { it.id == category.id }
}
