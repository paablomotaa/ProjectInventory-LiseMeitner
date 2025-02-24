package app.domain.invoicing.converter

import androidx.room.TypeConverter
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
}