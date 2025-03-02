package app.base.ui

import android.icu.text.SimpleDateFormat
import java.util.Date

fun validateEmail(email: String ): Boolean {
    val emailPattern = "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$" //Expresion regular
    val regex = Regex(emailPattern)
    return regex.matches(email)
    //return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validationDate(date: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    dateFormat.isLenient = false

    return try {
        val parsedDate = dateFormat.parse(date)

        val currentDate = Date()

        parsedDate.before(currentDate)
    } catch (e: Exception) {
        false
    }
}

fun validatePassword(password: String): Boolean{
    val passwordPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"
    val regex = Regex(passwordPattern)
    return regex.matches(password)
}