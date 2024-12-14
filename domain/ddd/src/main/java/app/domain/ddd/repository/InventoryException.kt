package app.domain.ddd.repository

sealed class InventoryException(message:String): Exception(message) {
    data object TakenCode: InventoryException("Ya existe una cuenta con ese")
    data object Idle: InventoryException("No hay error")
}