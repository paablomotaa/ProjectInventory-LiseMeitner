package app.domain.invoicing.converter

import androidx.room.TypeConverter
import app.domain.invoicing.account.Email
import app.domain.invoicing.product.Product
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class Converters{

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(formatter)  // Convierte LocalDate a String
    }

    // Convierte String a LocalDate al recuperar de la base de datos
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, formatter) }  // Convierte String a LocalDate
    }
    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time
    }
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun toEmail(value: String?): Email? {
        return value?.let {Email(it)}
    }

    @TypeConverter
    fun fromEmail(value: Email?): String? {
        return value?.value
    }
    @TypeConverter
    fun fromProductList(products: List<Product>?): String {
        return Gson().toJson(products)
    }
    @TypeConverter
    fun toProductList(data: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(data, listType)
    }
}