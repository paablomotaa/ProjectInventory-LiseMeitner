package app.domain.invoicing.inventory

import java.util.Date

data class inventory(
    val id:Long,
    val code:String,
    val name:String,
    val shortName:String,
    val description:String,
    val type:String,
    val dateProgress: Date,
    val dateActive: Date,
    val dateHistory: Date
    )