package app.domain.invoicing.section

import app.domain.invoicing.dependency.Dependency
import java.util.Date

data class Section(
    val id: String,
    val name: String,
    val shortName: String,
    val description: String,
    val dependency: Dependency,
    val imageUrl:String?,
    val createdDate: Date,
)
