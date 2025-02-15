package app.domain.ddd.repository

import app.base.utils.BaseResult
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.util.Date

object InventoryRepository {
    private var dataSet:MutableList<Inventory> = mutableListOf()
    init{
        initialize()
    }
    private fun initialize(){
        dataSet.add(Inventory(
            id = 1,
            code = "237123127",
            name = "InventarioEjemplo",
            shortName = "IE",
            description = "Este es un inventario de ejemplo",
            type = "Tipo random",
            dateActive = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
            dateHistory = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
        )
        )
    }
    suspend fun getData(): Flow<List<Inventory>> {
        delay(2000)
        return flow{emit(dataSet)}
    }
    suspend fun add(inventory: Inventory):BaseResult<Unit>{
        dataSet.add(inventory)
        return try{
            BaseResult.Success(Unit)
        } catch(exception:Exception){
            BaseResult.Error(exception)
        }
    }
    @Suppress("SuspiciousIndentation")
    suspend fun delete(id:Int){
        val inventoryToDelete = dataSet.find { it.id == id }
        if(inventoryToDelete != null)
        dataSet.remove(inventoryToDelete)
    }
    suspend fun edit(inventory: Inventory): Boolean {
        val inventoryToUpdate = dataSet.find { it.id == inventory.id }
        if (inventoryToUpdate != null) {
            dataSet.remove(inventoryToUpdate)
            dataSet.add(inventory)
            return true
        }
        return false
    }
    suspend fun isDuplicate(code:String):Boolean{
        return dataSet.any{it.code == code}
    }
    suspend fun existInventory(id:Int):Boolean{
        return dataSet.any{it.id == id}
    }
    suspend fun findInventory(id: Int): Inventory? {
        val inventory = dataSet.find { it.id == id }
        return inventory
    }
}