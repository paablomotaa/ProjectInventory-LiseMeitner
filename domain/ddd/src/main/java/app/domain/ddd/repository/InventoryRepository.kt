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
    suspend fun delete(inventario:Inventory){
        dataSet.remove(inventario)
    }
    suspend fun edit(
        id:Int,
        code:String,
        name:String,
        description:String,
        shortName:String,
        type:String,
        dateProgress:Date,
        dateActive:Date,
        dateHistory:Date
    ){
        var inventario = dataSet.firstOrNull{it.id == id}
        var inventario2 = Inventory(
            id = id,
            code = code,
            name = name,
            description = description,
            shortName = shortName,
            type = type,
            dateProgress = dateProgress,
            dateActive = dateActive,
            dateHistory = dateHistory,
        )
        inventario = inventario2
    }
    suspend fun isDuplicate(code:String):Boolean{
        return dataSet.any{it.code == code}
    }
    suspend fun existInventory(inventory: Inventory):Boolean{
        return dataSet.any{it.code == inventory.code}
    }
    suspend fun findInventory(id: Int?):Inventory?{
        return dataSet.find { it.id == id }
    }
}