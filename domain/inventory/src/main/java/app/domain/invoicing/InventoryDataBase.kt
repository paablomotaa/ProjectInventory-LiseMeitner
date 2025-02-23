package app.domain.invoicing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.base.utils.Status
import app.domain.invoicing.dao.ProductDao
import app.domain.invoicing.product.Product
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.concurrent.Executors

@Database(
    version = 1,
    entities = [Product::class],
    exportSchema = false
)
abstract class InventoryDataBase:RoomDatabase(){
    abstract fun getProductDao():ProductDao

    companion object {
        /**
         * La variable se guarda en memoria. Cualquier cambio realizado en la variable por un hilo
         * se refleja de inmediado y es visible al resto de hilos. No hay copias antiguas o nulas.
         */
        @Volatile
        private var INSTANCE: InventoryDataBase? = null

        fun getDatabase(context: Context): InventoryDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDataBase::class.java,
                    "login_database.db"
                )
                    // Callback para pre-poblar la base de datos
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se utiliza un executor para realizar la inserción en un hilo de fondo
                            //Las tareas se ejecutan de forma secuencial en un hilo/s
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let { database ->
                                    prepopulateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun prepopulateDatabase(database: InventoryDataBase) {
            val productDao = database.getProductDao()

            runBlocking {
                productDao.insertProduct(
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

        }
    }
}