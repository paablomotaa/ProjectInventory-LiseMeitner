package app.domain.invoicing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import app.base.utils.Status
import app.domain.invoicing.account.Email
import app.domain.invoicing.converter.Converters
import app.domain.invoicing.dao.InventoryDao
import app.domain.invoicing.dao.ProductDao
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.product.Product
import com.example.login.data.dao.AccountDao
import com.example.login.data.dao.BusinessDao
import com.example.login.data.dao.PersonalDao
import com.example.login.data.model.Account
import com.example.login.data.model.Address
import com.example.login.data.model.Business
import com.example.login.data.model.Personal
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors

@Database(
    version = 1,
    entities = [Product::class,Inventory::class,Account::class, Personal::class, Business::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDataBase:RoomDatabase(){
    abstract fun getProductDao():ProductDao
    abstract fun getInventoryDao():InventoryDao
    abstract fun getAccountDao(): AccountDao
    abstract fun getPersonalDao(): PersonalDao
    abstract fun getBusinessDao(): BusinessDao

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
            val accountDao = database.getAccountDao()
            val personalDao = database.getPersonalDao()
            val businessDao = database.getBusinessDao()

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
                accountDao.insert(
                    Account(
                        id = 1,
                        email = Email("antoniojosenietoalba@gmail.com"),
                        password = "1981Minecr@ft",
                        userName = "AntonioGar",
                        name = "Antonio",
                        surname = "Garci",
                        dateOfBirth = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2000/01/01")
                    )
                )
                accountDao.insert(
                    Account(
                        id = 2,
                        email = Email("antoniojosenietoalbg@gmail.com"),
                        password = "1981Minicr@ft",
                        userName = "AleZe",
                        name = "Ale",
                        surname = "Gil",
                        dateOfBirth = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2000/01/01")
                    )
                )
                accountDao.insert(
                    Account(
                        id = 3,
                        email = Email("antoniojosenietoalbz@gmail.com"),
                        password = "1981Minepr@ft",
                        userName = "Zingagr",
                        name = "Zinga",
                        surname = "Lopez",
                        dateOfBirth = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2000/01/01")
                    )
                )
                personalDao.insert(
                    Personal(
                        idAccount = 2,
                        nif = "954482965",
                        direction = Address(
                            idAddress = 1,
                            street = "Calle Falsa",
                            city = "Málaga",
                            postalCode = 29006,
                            country = "España"
                        )
                    )
                )
                businessDao.insert(
                    Business(
                        id = 1,
                        idAccount = 1,
                        nameCompany = "IES Portada Alta",
                        cif = "859414563",
                        direction = Address(
                            idAddress = 1,
                            street = "Calle Falsa123",
                            city = "Madrid",
                            postalCode = 28006,
                            country = "España"
                        )
                    )
                )
            }
        }
    }
}