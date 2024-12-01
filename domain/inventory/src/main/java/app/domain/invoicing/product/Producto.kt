package app.domain.invoicing.product

import app.base.utils.Estado
import java.util.Date

data class Producto(val id: Long,
                    val codigo: String,
                    val nombre: String,
                    val nombreCorto: String,
                    val descripcion: String,
                    val numSerie: Double,
                    val codModelo: String,
                    val tipoProducto: String,
                    val categoria: Categoria,
                    val seccion: Seccion,
                    val estado: Estado,
                    val cantidad: Int,
                    val precio: Double,
                    val imagen: String,
                    val fechaAdquisicion: Date,
                    val fechaBaja: Date,
                    val notas: String,
                    val tags: String){

}
