package app.domain.invoicing.inventory

import java.util.Date

data class Inventory(
    val id:Int,
    val code:String,
    val name:String,
    val shortName:String,
    val description:String,
    val type:String?,
    val dateActive: Date?,
    val dateProgress: Date? = dateActive,
    val dateHistory: Date?
    ){
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}