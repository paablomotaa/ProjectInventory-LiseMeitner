package app.base.utils

fun isValidShortName(shortname: String): Boolean {
    val regex = "^[a-zA-Z0-9\\s]+\$".toRegex()
    return shortname.matches(regex)
}