package com.moronlu18.loginjetpackcompose.login

import com.example.login.data.model.AccountException


data class LoginState(
    var email: String = "",
    var password: String = "",

    //RNLOGIN_1 : Formato Passqword incorrecto
    // 8 caracteres mínimo (ya se contempla el nulo)
    // A-> 1 carácter mayúscula
    // 2-> mínimio 1 carácter número
    // ? -> 1 caracter especial
    val passwordErrorFormat: String? = null,

    //RNLOGIN_2 : Formato email incorrecto
    // pattern y matcher
    val emailErrorFormat: String? = null,

    //RNLOGIN_3 : Usuario no registrado
    val userError: String? = null,


    //RNLOGIN_4 : Success
    var success: Boolean = false,

    //Requerimientos NO Funcional
    //RNFLOGIN_1 : Tiempo de espera de conexión
    var isLoading: Boolean = false,

    //RNFLOGIN_2 : Error de conexión
    val isOffline: Boolean = false,

    //Se crean los booleanos necesarios para saber si hay un error en el email
    val isEmailError: Boolean = false,

    val isPasswordError: Boolean = false,

    val passwordVisible: Boolean = false,

    val accountError: AccountException = AccountException.Idle,
    val isErrorAccount: Boolean = true,
    )