package app.domain.invoicing.product

sealed class ProductException(message:String): Exception(message) {
    data class CreatedProduct(var code: Long): ProductException("Ya existe")
    data object NotFoundProduct: ProductException("La  no existe")
    //Devuelve el toString
    data object Idle: ProductException("No hay error")
}