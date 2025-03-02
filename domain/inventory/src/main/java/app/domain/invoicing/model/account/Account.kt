package com.example.login.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.domain.invoicing.account.Email
import java.text.SimpleDateFormat
import java.util.Date
import java.time.LocalDate
import java.util.Locale
import java.util.UUID
import kotlin.math.absoluteValue

@Entity
data class Account constructor(
    // @Nonnull para que sea no nulosp ero como tienen ? no hace falta en kotlin
    @PrimaryKey
    val id: Int,
    val email: Email,
    val password: String,
    val name: String,
    val surname: String,
    val dateOfBirth: Date?,//Date?
    @ColumnInfo(name="display_name")
    val userName: String?,
    @ColumnInfo(name="photo_url")
    var photoURL: String? = null,
    @ColumnInfo(name="firebase_uid")
    var fireBaseUID: String? = null,
    @ColumnInfo(name="creation_at")
    val creationDate: String = LocalDate.now().toString(),
    @ColumnInfo(name="update_at")
    val updateDate: String = LocalDate.now().toString(),

    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    companion object {

        private fun getCurrentTimestamp(): String {
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            return sdf.format(Date())  // Convierte la fecha actual en String
        }

        // Método de fábrica: puede servir para añadir validaciones u otra lógica

        fun create(
            id: Int,
            email: Email,
            password: String,
            name: String,
            surname: String,
            userName: String,
            dateOfBirth: Date?,
            firebaseUID: String?,

            ): Account {
            // Aquí se podrían agregar validaciones, transformación de datos, etc.
            // Validaciones básicas
            require(password.length >= 6) { "La contraseña debe tener al menos 6 caracteres." }
            require(name.isNotBlank()) { "El nombre no puede estar vacío." }
            require(surname.isNotBlank()) { "El apellido no puede estar vacío." }

            return Account( id, email, password, name, surname, dateOfBirth, userName, null, firebaseUID, getCurrentTimestamp(), getCurrentTimestamp())
        }
        fun generarCuentaId(): Int {
            return UUID.randomUUID().hashCode().absoluteValue % 900000 + 100000
        }

    }
}