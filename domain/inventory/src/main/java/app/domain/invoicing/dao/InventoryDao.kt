package app.domain.invoicing.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Insert
    suspend fun insert(inventory: Inventory)

    @Delete
    suspend fun delete(inventory: Inventory)

    @Update
    suspend fun update(inventory: Inventory)

    @Query("SELECT * from inventory")
    fun getAllInventories(): Flow<List<Inventory>>
    @Query("SELECT * from inventory WHERE id=:inventoryId")
    fun getInventoryById(inventoryId:Int):Inventory
    @Query("SELECT * from inventory WHERE code=:inventoryCode")
    fun getInventoryByCode(inventoryCode:String):Inventory
}