package app.domain.invoicing.inventory

import java.util.Date

data class Inventory(
    val id:Long,
    val code:String,
    val name:String,
    val shortName:String,
    val description:String,
    val type:String?,
    val dateActive: Date?,
    val dateProgress: Date? = dateActive,
    val dateHistory: Date?
    )