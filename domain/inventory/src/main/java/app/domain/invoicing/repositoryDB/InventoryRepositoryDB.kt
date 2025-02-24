package app.domain.invoicing.repositoryDB

import app.base.utils.BaseResult
import app.domain.invoicing.dao.InventoryDao
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepositoryDB @Inject constructor(private val inventoryDao: InventoryDao) {
    suspend fun insertInventory(inventory: Inventory):BaseResult<Unit>{
        inventoryDao.insert(inventory)
        return try{
            BaseResult.Success(Unit)
        } catch(exception:Exception){
            BaseResult.Error(exception)
        }
    }

    suspend fun deleteInventory(inventory: Inventory){
        inventoryDao.delete(inventory)
    }

    suspend fun update(inventory: Inventory){
        inventoryDao.update(inventory)
    }

    fun getData(): Flow<List<Inventory>> {
        return inventoryDao.getAllInventories()
    }

    fun getDataById(inventoryId:Int):Inventory{
        return inventoryDao.getInventoryById(inventoryId)
    }
    fun getDataByCode(inventoryCode:String):Inventory{
        return inventoryDao.getInventoryByCode(inventoryCode)
    }
}