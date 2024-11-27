package app.base.utils

fun validateEmail(email: String ): Boolean {
    val emailPattern = "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$" //Expresion regular
    val regex = Regex(emailPattern)
    return regex.matches(email)
    //return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}