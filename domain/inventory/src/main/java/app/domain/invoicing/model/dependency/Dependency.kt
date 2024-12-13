package app.domain.invoicing.dependency

data class Dependency(
    val id: String, // Identificador único
    val name: String, // Nombre completo
    val shortName: String, // Nombre corto
    val description: String, // Descripción
    val imageUrl: String? = null // Imagen de la dependencia (opcional)
)
