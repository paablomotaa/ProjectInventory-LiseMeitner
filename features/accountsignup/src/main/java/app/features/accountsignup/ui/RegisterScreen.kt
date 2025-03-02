package com.example.login.ui.feature.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseField
import app.base.ui.composables.DateField
import app.base.ui.composables.DialogDate
import app.base.ui.composables.DialogDate2
import app.base.ui.composables.EmailField
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.StructureScreen
import app.base.ui.composables.TitleTxt
import com.example.login.base.Composable.BaseField
import com.example.login.base.Composable.DateField
import com.example.login.base.Composable.DialogDate
import com.example.login.base.Composable.EmailField
import com.example.login.base.Composable.LoadingUI
import com.example.login.base.Composable.MediumSpace
import com.example.login.base.Composable.StructureScreen
import com.example.login.base.Composable.TitleTxt
import com.example.login.ui.feature.Account.AlertDialogOK
import com.moronlu18.loginjetpackcompose.register.RegisterState
import java.time.LocalDate

@Composable
fun RegisterScreen(
    goUp: () -> Unit,
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier,
    event: RegisterEvent = RegisterEvent(
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onNameUserChange = viewModel::onNameUserChange,
        onNameChange = viewModel::onNameChange,
        onSurnameChange = viewModel::onSurnameChange,
        onDateChange = viewModel::onDateChange,
        onShowDialogChange = viewModel::onShowDialogChange,
        onClickRegister = viewModel::onClickRegister,
        onDismissError = viewModel::onDismissError
    )
) {
    when {
        viewModel.state.isLoading -> LoadingUi()
        viewModel.state.accountExitsError -> AlertDialogOK(
            title = "Error",
            message = "Cuenta ya existe",
            onDismiss = {
                event.onDismissError()
                //viewModel.state.copy(accountExitsError = false) }
            }
        )

        viewModel.state.isEmpty -> AlertDialogOK(
            title = "Error",
            message = "Campos vacios",
            onDismiss = {
                event.onDismissError()
                //viewModel.state.copy(isEmpty = false) }
            }
        )

        else -> RegisterScreenContent(
            modifier,
            viewModel = viewModel, goUp, event
        )
    }
}

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    goUp: () -> Unit,
    event: RegisterEvent
) {

    RegisterUi(modifier, state = viewModel.state, goUp, event)
}

data class RegisterEvent(
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onNameUserChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onSurnameChange: (String) -> Unit = {},
    val onDateChange: (LocalDate) -> Unit = {},
    val onShowDialogChange: (Boolean) -> Unit = {},
    val onClickRegister: (() -> Unit) -> Unit = {},
    val onDismissError: () -> Unit = {}
)

@Composable
fun RegisterUi(
    modifier: Modifier = Modifier,
    state: RegisterState,
    goUp: () -> Unit,
    event: RegisterEvent = RegisterEvent()
) {

    StructureScreen(
        modifier = modifier
    ) {
        TitleTxt("Register")

        MediumSpace()

        EmailField(state.isEmailError, state.email, event.onEmailChange, state.emailErrorFormat)

        BaseField(
            text = "Contraseña",
            state.password,
            event.onPasswordChange,
            state.isPasswordError,
            state.passwordErrorFormat,
            modifier = Modifier.testTag("passwordField")
        )

        BaseField(
            "Usuario",
            state.nameUser,
            event.onNameUserChange,
            state.isNameUserError,
            state.nameUserErrorFormat
        )

        BaseField(
            "Nombre",
            state.name,
            event.onNameChange,
            state.isNameError,
            state.userErrorFormat
        )

        BaseField(
            "Apellidos",
            state.surname,
            event.onSurnameChange,
            state.isSurnameError,
            state.userErrorFormat
        )

        DateField(event.onShowDialogChange, state.date, state.isDateError, state.dateError)

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { event.onClickRegister(goUp) },
        ) {
            Text(text = "Register")
        }

        DialogDate2(state.showDialog, event.onShowDialogChange, event.onDateChange)
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
}