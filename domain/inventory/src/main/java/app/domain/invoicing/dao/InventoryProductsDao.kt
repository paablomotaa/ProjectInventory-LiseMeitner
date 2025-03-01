package app.domain.invoicing.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.domain.invoicing.model.inventoryproducts.InventoryProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryProductsDao {
    @Insert
    suspend fun insert(inventory: InventoryProducts)

    @Delete
    suspend fun delete(inventory: InventoryProducts)

    @Update
    suspend fun update(inventory: InventoryProducts)

    @Query("SELECT * from inventory_products")
    fun getAllRelations(): Flow<List<InventoryProducts>>

    @Query("SELECT * from inventory_products WHERE id=:inventoryId")
    suspend fun getProductListById(inventoryId:Int): InventoryProducts

}