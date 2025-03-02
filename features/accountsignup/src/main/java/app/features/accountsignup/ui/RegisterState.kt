package com.moronlu18.loginjetpackcompose.register

data class RegisterState(
    var id: Int = 0,
    var nameUser: String = "",
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var password: String = "",
    var date: String = "",
    var showDialog: Boolean = false,
    var passwordVisible: Boolean = false,
    //RNREGISTER_1 : Formato Passqword incorrecto
    // 8 caracteres mínimo (ya se contempla el nulo)
    // A-> 1 carácter mayúscula
    // 2-> mínimio 1 carácter número
    // ? -> 1 caracter especial
    val passwordErrorFormat: String? = null,

    //RNREGISTER_2 : Formato email incorrecto
    // pattern y matcher
    val emailErrorFormat: String? = null,


    //RNREGISTER_3 : Formato name user incorrecto
    // pattern y matcher
    val nameUserErrorFormat: String? = null,

    //RNREGISTER_4: Nombre y apellidos del usuario no nulo
    val userErrorFormat: String? = null,

    //RNREGISTER_5: Fecha posterior a la actual
    val dateError: String? = null,

    //RNREGISTER_6: Cuenta ya existe (name user, email)
    val accountExitsError: Boolean = false,

    //RNREGISTER_7:Error que venga de la infraestructura
    // (bases de dats, netflix...)
    val serverError: String? = null,

    //RNREGISTER_8 : Success
    val success: Boolean = false,


    //Requerimientos NO Funcional
    //RNFLOGIN_1 : Tiempo de espera de conexión
    val displayNameErrorFormat: String? = null,
    val isDisplayNameError: Boolean = false,

    val isLoading: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isNameUserError: Boolean = false,
    val isNameError: Boolean = false,
    val isSurnameError: Boolean = false,
    val isDateError: Boolean = false,

    val isEmpty: Boolean = false,

)