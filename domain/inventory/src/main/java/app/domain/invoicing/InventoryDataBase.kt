package app.domain.invoicing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import app.base.utils.Status
import app.domain.invoicing.converter.Converters
import app.domain.invoicing.dao.InventoryDao
import app.domain.invoicing.dao.ProductDao
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.product.Product
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.Date
import java.util.concurrent.Executors

@Database(
    version = 1,
    entities = [Product::class,Inventory::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDataBase:RoomDatabase(){
    abstract fun getProductDao():ProductDao
    abstract fun getInventoryDao():InventoryDao

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
            val inventoryDao = database.getInventoryDao()

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
                inventoryDao.insert(
                    Inventory(
                        id = 1,
                        code = "237123127",
                        name = "InventarioEjemplo",
                        shortName = "IE",
                        description = "Este es un inventario de ejemplo",
                        type = "Tipo random",
                        dateActive = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
                        dateHistory = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
                    )
                )
            }
        }
    }
}