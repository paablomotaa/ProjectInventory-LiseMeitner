package app.domain.invoicing.repository

import app.base.utils.Status
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.domain.invoicing.product.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.util.Date

object ProductRepository {

    private val productsSet: MutableList<Product> = mutableListOf()

    init {
        inicializeProducts()
    }

    private fun inicializeProducts() {
        productsSet.add(
            Product(
                id = 1,
                code = "001",
                name = "PRODUCTO PRUEBA",
                shortName = "PRODUCTO PRUEBA",
                description = "PRODUCTO PRUEBA",
                numSerial = 1.0,
                codModel = "001",
                typeProduct = "PRODUCTO PRUEBA",
                category = "h",
                section = "",
                status = Status.NEW,
                amount = 1,
                price = 1.0,
                image = "",
                acquisitionDate = LocalDate.now(),
                cancellationDate = LocalDate.now(),
                notes = "",
                tags = "dad"
            )
        )
    }

    suspend fun getProduct(): Flow<List<Product>> {
        delay(1000)

        return flow { emit(productsSet) }
    }

    suspend fun getStatus(): Flow<List<Status>> {
        delay(1000)
        return flow { emit(Status.entries)}
    }

    suspend fun existProduct(code: String): Boolean {
        delay(1000)
        return productsSet.any {it.code == code }
    }

    suspend fun createProduct(

        id: Long,
        code: String,
        name: String,
        shortName: String,
        description: String,
        numSerial: Double,
        codModel: String,
        typeProduct: String,
        category: String,
        section: String,
        status: Status,
        amount: Int,
        price: Double,
        image: String,
        acquisitionDate: LocalDate,
        cancellationDate: LocalDate,
        tags: String,
        notes: String
    ): Boolean {
        delay(2000)
        return productsSet.add(
            Product(
                id,
                code,
                name,
                shortName,
                description,
                numSerial,
                codModel,
                typeProduct,
                category,
                section,
                status,
                amount,
                price,
                image,
                acquisitionDate,
                cancellationDate,
                tags,
                notes
            )
        )
    }

    suspend fun updateProduct(
        id: Long,
        code: String,
        name: String,
        shortName: String,
        description: String,
        numSerial: Double,
        codModel: String,
        typeProduct: String,
        category: String,
        section: String,
        status: Status,
        amount: Int,
        price: Double,
        image: String,
        acquisitionDate: LocalDate,
        cancellationDate: LocalDate,
        tags: String,
        notes: String
    ): Boolean {
        delay(2000)
        val index = productsSet.indexOfFirst { it.id == id }
        return if (index != -1) {
            productsSet[index] = Product(
            id,
            code,
            name,
            shortName,
            description,
            numSerial,
            codModel,
            typeProduct,
            category,
            section,
            status,
            amount,
            price,
            image,
            acquisitionDate,
            cancellationDate,
            tags,
            notes
        )
            true
        }else{
            false
        }
    }

    suspend fun deleteProduct(product: Product): Boolean {
        delay(2000)
        return productsSet.remove(product)
    }

}