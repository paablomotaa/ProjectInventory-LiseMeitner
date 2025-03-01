package app.domain.invoicing.repositoryDB

import app.base.utils.BaseResult
import app.domain.invoicing.dao.InventoryProductsDao
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.model.inventoryproducts.InventoryProducts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryProductsRepositoryDB @Inject constructor(private val inventoryProductsDao: InventoryProductsDao) {
    suspend fun insertInventory(inventoryproducts: InventoryProducts): BaseResult<Unit> {
        inventoryProductsDao.insert(inventoryproducts)
        return try{
            BaseResult.Success(Unit)
        } catch(exception:Exception){
            BaseResult.Error(exception)
        }
    }

    suspend fun deleteRelation(inventoryproducts: InventoryProducts){
        inventoryProductsDao.delete(inventoryproducts)
    }

    suspend fun update(inventoryproducts: InventoryProducts){
        inventoryProductsDao.update(inventoryproducts)
    }

    suspend fun getData(): Flow<List<InventoryProducts>> {
        return inventoryProductsDao.getAllRelations()
    }

    suspend fun getDataById(inventoryId:Int): InventoryProducts {
        return inventoryProductsDao.getProductListById(inventoryId)
    }
}