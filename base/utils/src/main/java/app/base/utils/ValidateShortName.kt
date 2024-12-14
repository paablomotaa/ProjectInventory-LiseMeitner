package app.base.utils

fun isValidShortName(shortname: String): Boolean {
    val regex = "^[a-zA-Z0-9]{3}$".toRegex()
    return shortname.matches(regex)
}