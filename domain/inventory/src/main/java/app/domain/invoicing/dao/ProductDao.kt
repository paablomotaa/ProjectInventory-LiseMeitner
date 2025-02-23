package app.domain.invoicing.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.domain.invoicing.product.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM Product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :productId")
    fun getProductById(productId: Int): Product?

    @Query("SELECT * FROM Product WHERE code = :productCode")
    fun validate(productCode: String): Product?

}