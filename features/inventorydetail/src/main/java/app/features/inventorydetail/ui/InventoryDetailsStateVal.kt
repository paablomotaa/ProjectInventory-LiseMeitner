package app.features.inventorydetail.ui

import java.time.LocalDate
import java.util.Date

data class InventoryDetailsStateVal(
    val id: Int = 0,
    val code:String = "",
    val name:String = "",
    val shortName:String = "",
    val description:String = "",
    val type:String? = "",
    val dateActive: Date = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
    val dateProgress: Date = dateActive,
    val dateHistory: Date = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
)