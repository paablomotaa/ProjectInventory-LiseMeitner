package com.example.login.ui.feature.login

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.ui.validateEmail
import app.base.ui.validatePassword
import app.base.utils.BaseResult
import app.domain.invoicing.account.Email
import app.domain.invoicing.repositoryDB.AccountRepositoryDB
import com.example.login.data.model.AccountException
import app.domain.invoicing.Session
import com.moronlu18.loginjetpackcompose.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "ViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepositoryDB,
    //private val resources: Resources,
    private val session: Session
) : ViewModel() {
    //1. Solucion google
    /*
    private val _stateGoogle = mutableStateOf(LoginState())
    val stateGoogle: State<LoginState> = _stateGoogle
    */

    //2. Solucion Lourdes
    var state by mutableStateOf(LoginState())
        private set

    /**
     * On email change comprueba que el email sea correcto
     *
     * @param email
     */

    fun onEmailChange(email: String) {
        /*
        //0. Eliminar el campo emailErrorFormat
        state.emailErrorFormat.let {
            state = state.copy(emailErrorFormat = null, isEmailError = false)
        }*/

        //1. Si el usuario pulsa el caracter en blanco, no se tiene en cuenta
        if (email.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        if (!validateEmail(email))
            state = state.copy(
                isEmailError = true,
                emailErrorFormat = "Formato de email incorrecto",
                email = email
            )
        else
            state = state.copy(isEmailError = false, emailErrorFormat = null, email = email)
    }

    fun onPasswordChange(password: String) {
        //1. Si el usuario pulsa el caracter en blanco, no se tiene en cuenta
        if (password.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        if (!validatePassword(password))
            state = state.copy(
                isPasswordError = true,
                passwordErrorFormat = "Minimo 8 caracteres, una mayuscula, una minuscila, un numero y un caracter especial",
                password = password
            )
        else
            state =
                state.copy(isPasswordError = false, passwordErrorFormat = null, password = password)
    }

    fun onClickPassword(isPasswordVisible: Boolean) {
        state = state.copy(passwordVisible = !isPasswordVisible)
    }

    fun onClickLogin(navRegister: () -> Unit) {
        state = state.copy(
            isEmailError = state.email.isEmpty(),
            isPasswordError = state.password.isEmpty()
        )
        if (state.email.isNotEmpty() && state.password.isNotEmpty() && !state.isEmailError && !state.isPasswordError) {
            //1. La vista se actualiza a Loading
            state = state.copy(isLoading = true)
            //2. Se crea una corrutina con scope Default
            viewModelScope.launch {
                val response = repository.validate(Email(state.email), state.password)
                when (response) {
                    is BaseResult.Error -> {
                        state = state.copy(
                            isErrorAccount = true,
                            accountError = response.message as AccountException,
                            emailErrorFormat = response.message.toString(),
                            isLoading = false
                        )
                    }

                    is BaseResult.Success<*> -> {
                        state = state.copy(success = true)
                        session.saveUserSession(state.email, state.password, isUserLoggedIn = true)
                        navRegister()
                        Log.d(TAG, response.data.toString())
                    }
                }
            }
        } else {
            state = state.copy(
                passwordErrorFormat = if (state.isPasswordError) "Formato incorrecto" else null,
                emailErrorFormat = if (state.isEmailError) "Formato incorrecto" else null
            )
        }
    }

    /**
     * Establece los parametros que tiene en SignUp
     *
     * @param email
     * @param password
     */
    fun setCredentialsFromSignUp(email: String, password: String) {
        state = state.copy(email = email, password = password)
    }

    fun onSingUpLogin(navAccountList: () -> Unit) {
        navAccountList()
    }
}