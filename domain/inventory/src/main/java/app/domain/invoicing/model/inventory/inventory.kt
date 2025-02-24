package app.domain.invoicing.inventory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "inventory")
data class Inventory(
    @PrimaryKey
    val id:Int,
    val code:String,
    val name:String,
    val shortName:String,
    val description:String,
    val type:String = "",
    @ColumnInfo(name = "date_Active")
    val dateActive: Date?,
    @ColumnInfo(name = "date_Progress")
    val dateProgress: Date? = dateActive,
    @ColumnInfo(name = "date_History")
    val dateHistory: Date?
    ){
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return name
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}