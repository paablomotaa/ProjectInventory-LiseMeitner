package app.domain.invoicing.repositoryDB

import app.base.utils.Status
import app.domain.invoicing.dao.ProductDao
import app.domain.invoicing.product.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductRepositoryDB @Inject constructor(private val productDao: ProductDao) {

    suspend fun save(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun remove(product: Product) {
        productDao.deleteProduct(product)
    }

    fun getAll(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    suspend fun update(product: Product) {
        productDao.updateProduct(product)
    }

    fun getById(id: Long): Flow<Product?> {
        return productDao.getProductById(id)
    }

    suspend fun validate(code: String): Boolean {
        return productDao.validate(code) != null
    }

    /*
    fun getStatus(): Flow<List<Status>> {
        return
    }
    */

}