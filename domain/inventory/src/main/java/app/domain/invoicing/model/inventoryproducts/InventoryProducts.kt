package app.domain.invoicing.model.inventoryproducts

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.domain.invoicing.product.Product

@Entity(tableName = "inventory_products")
data class InventoryProducts(
    @PrimaryKey
    val id:Int,
    val products:List<Product>
){
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
    override fun toString(): String {
        return id.toString()
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}