package app.features.accountsignup.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.ui.validatePassword
import app.base.ui.validationDate
import app.base.utils.BaseResult
import app.base.utils.*
import app.domain.invoicing.account.Email
import app.domain.invoicing.repositoryDB.AccountRepositoryDB
import com.example.login.data.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

const val TAG = "ViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AccountRepositoryDB) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    fun onNameUserChange(nameUser: String) {
        if (nameUser.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        state = state.copy(isNameUserError = false, nameUserErrorFormat = null, nameUser = nameUser)
    }

    fun onNameChange(name: String) {
        if (name.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        state = state.copy(isNameError = false, userErrorFormat = null, name = name)
    }

    fun onSurnameChange(surname: String) {
        if (surname.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        state = state.copy(isSurnameError = false, userErrorFormat = null, surname = surname)
    }

    fun onEmailChange(email: String) {
        //1. Si el usuario pulsa el caracter en blanco, no se tiene en cuenta
        if (email.contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        if (!validateEmail(email))
            state = state.copy(
                isEmailError = true,
                emailErrorFormat = "Formato incorrecto",
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

    fun onDateChange(date: LocalDate) {

        //1. Si el usuario pulsa el caracter en blanco, no se tiene en cuenta
        if (date.toString().contains(' ')) return

        //2. Si no es válido se modifica los valores del state
        if (!validationDate(date.toString()))
            state = state.copy(
                isDateError = true,
                dateError = "Fecha muy grande",
                date = date.toString()
            )
        else{
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            state = state.copy(isDateError = false, dateError = null, date = date.format(formatter))
        }
    }

    fun onShowDialogChange(showDialog: Boolean) {
        state = state.copy(showDialog = showDialog)
    }

    @SuppressLint("SuspiciousIndentation")
    fun onClickRegister(goBack:() ->Unit) {
        if(areFieldEmpty()){
            state = state.copy(isEmpty = true)
            return
        }
        /*state = state.copy(
            isEmailError = state.email.isEmpty() && !validateEmail(state.email),
            isNameUserError = state.nameUser.isEmpty(),
            isNameError = state.name.isEmpty(),
            isSurnameError = state.surname.isEmpty(),
            isDateError = state.date.isEmpty() && !validationDate(state.date),
            isPasswordError = state.password.isEmpty() && !validatePassword(state.password)
        )*/
        if(hasValidationErrors()) return

        //if (!state.isEmailError && !state.isPasswordError && !state.isNameUserError && !state.isNameError && !state.isSurnameError && !state.isDateError) {
            //1. La vista se actualiza a Loading
            state = state.copy(isLoading = true)
            //2. Se crea una corrutina con scope Default

            viewModelScope.launch {
                val response = repository.exist(state.email)
                if (response != false) {
                    state = state.copy(isLoading = false)
                    state = state.copy(accountExitsError = true)
                    Log.d(TAG, "Account exist")
                } else {
                    val cuenta = Account(id = state.id,
                        email = Email(state.email),
                        password = state.password,
                        userName = state.nameUser,
                        name = state.name,
                        surname = state.surname,
                        dateOfBirth = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(state.date)
                    )
                    val accountCreate = repository.create(
                        cuenta
                    )
                    if (accountCreate is BaseResult.Success<*>) {
                        state = state.copy(success = true, isLoading = false)
                        Log.d(TAG, "Account create")
                        goBack()
                    } else {
                        state = state.copy(isLoading = false)
                        Log.d(TAG, "Account not create")
                    }
                }
            }
        /*} else {
            state = state.copy(
                passwordErrorFormat = if (state.isPasswordError) "Formato incorrecto" else null,
                dateError = if (state.isDateError) "Formato incorrecto" else null,
                emailErrorFormat = if (state.isEmailError) "Formato incorrecto" else null,
                nameUserErrorFormat = if (state.isNameUserError) "Formato incorrecto" else null,
                userErrorFormat = if (state.isNameError) "Formato incorrecto" else null
            )
        }*/
    }

    private fun areFieldEmpty(): Boolean {
        return state.email.isEmpty() || state.password.isEmpty() || state.nameUser.isEmpty() || state.name.isEmpty() || state.surname.isEmpty() || state.date.isEmpty()
    }

    private fun hasValidationErrors(): Boolean {
        return state.isEmailError || state.isPasswordError || state.isNameUserError || state.isNameError || state.isSurnameError || state.isDateError
    }

    fun onDismissError() {
        state = state.copy(
            isEmpty = false,
            accountExitsError = false
        )
    }
}