package app.domain.invoicing.model.category

sealed class CategoryException(message: String) : Exception(message) {
    data object DuplicateCategory : CategoryException("Ya existe una categoría con ese ID")
    data object Idle : CategoryException("No hay error")
    data object CategoryNotFound : CategoryException("No se encontró la categoría con el ID especificado")
}