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
            // Se inicializan las categorías predeterminadas
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

    suspend fun addCategory(category: Category) {
        dataSet.add(category) // Se agrega la categoría a la lista
    }

    suspend fun deleteCategory(category: Category) {
        dataSet.remove(category) // Se elimina la categoría de la lista
        delay(100)
    }

    suspend fun editCategory(category: Category) {
        val index = dataSet.indexOfFirst { it.id == category.id }
        if (index != -1) {
            dataSet[index] = category // Se actualiza la categoría en la lista
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

    // Este método devuelve un Flow de las categorías para que la UI pueda recolectarlas
    fun getAllCategories(): Flow<List<Category>> {
        return flow {
            delay(2000) // Simulamos un retraso en la carga de los datos
            emit(dataSet) // Emitimos las categorías actuales
        }
    }

}