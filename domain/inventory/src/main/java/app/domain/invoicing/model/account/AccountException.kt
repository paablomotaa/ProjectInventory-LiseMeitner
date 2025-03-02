package com.example.login.data.model

import app.domain.invoicing.account.Email

/**
 * Account exception contiene todos los posibles errores qu se pueden
 * presentar en el caso de uso de login t de signup
 *
 * @constructor
 *
 * @param message
 */
sealed class AccountException(message: String): Exception(message){
    data class TakenEmail(var email: Email): AccountException("Ya existe")
    data object NoExistAccount: AccountException("La cuenta no existe")
    //Devuelve el toString
    data object Idle: AccountException("No hay error")
}