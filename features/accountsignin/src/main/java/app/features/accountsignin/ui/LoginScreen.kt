package app.features.accountsignin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseImageSmall
import app.base.ui.composables.EmailField
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.PasswordField
import app.base.ui.composables.SmallSpace
import app.base.ui.composables.StructureScreen
import app.base.ui.composables.TitleTxt

import app.features.accountsignin.R

/**
 * Login screen
 *
 */

@Composable
fun LoginScreen(
    email: String,
    password: String,
    viewModel: LoginViewModel,
    goToAccountList: () -> Unit,
    goToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    //Solo se ejecuta la primera vez, se pone como Key el tipo Unit. Se tiene que poner como key
    // el email y el password
    LaunchedEffect(email,password){
        if (email.isNotEmpty() && password.isNotEmpty()) {
            /*viewModel.onEmailChange(email)
            viewModel.onPasswordChange(password)*/
            viewModel.setCredentialsFromSignUp(email, password)
        }
    }
    when{
        viewModel.state.isLoading -> LoadingUi()
        viewModel.state.isOffline -> OfflineUI(modifier)
        //viewModel.state.isErrorAccount -> DialogUI()
        else -> LoginScreenContent(
            viewModel = viewModel,goToAccountList,goToSignUp, modifier,)
    }
}


//region de Lourdes

@Composable
fun LoginScreenContent(
    viewModel: LoginViewModel,
    goToAccountList: () -> Unit,
    goToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {


    LoginScreenLourdes(modifier, state = viewModel.state, goToAccountList, goToSignUp, event =
        LoginEvent(
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onClickPassword = viewModel::onClickPassword,
            onClickLogin = viewModel::onClickLogin,
            onSingUpLogin = viewModel::onSingUpLogin
        )
    )
}

data class LoginEvent(val onEmailChange: (String) -> Unit = {},
                      val onPasswordChange: (String) -> Unit= {},
                      val onClickPassword: (Boolean) -> Unit= {},
                      val onClickLogin: (() -> Unit) -> Unit= {},
                      val onSingUpLogin: (() -> Unit) -> Unit= {})

@Composable
fun LoginScreenLourdes(
    modifier: Modifier,
    state: LoginState,
    goToAccountList: () -> Unit,
    goToSignUp: () -> Unit,
    event: LoginEvent
) {

    StructureScreen( modifier = modifier){
        TitleTxt(stringResource(R.string.txt_login))
        Image(painter = painterResource(id = R.drawable.ic_launcher), contentDescription = "", modifier = Modifier.size(100.dp))
        MediumSpace()

        SmallSpace()

        EmailField(state.isEmailError,state.email,event.onEmailChange, state.emailErrorFormat)

        //PasswordField
        PasswordField(state.password, state.passwordVisible, state.isPasswordError, event.onPasswordChange, event.onClickPassword, state.passwordErrorFormat)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "¿No tienes una cuenta?")

            TextButton(onClick = {event.onSingUpLogin(goToSignUp)}) { Text(text = "CREAR") }
        }

        SmallSpace()

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {event.onClickLogin(goToAccountList)},
        ) {
            Text(text = stringResource(R.string.txt_login))
        }

    }
}
//endregion

/**
 * Offline error connection
 *
 */
@Composable
fun OfflineUI(modifier: Modifier = Modifier) {
    StructureScreen(modifier = modifier){
        Card {
            Image(
                painter = painterResource(R.drawable.ic_cloud_off),
                contentDescription = stringResource(id = R.string.errorDescription),
            )
        }
        //Un texto

        Text(text = stringResource(R.string.errorDescription))
    }
}
@Composable
fun LoginScreenPreview() {
    //LoginScreenLourdes(modifier = Modifier, loginState = LoginState())
}
@Preview(showBackground = true)
@Composable
fun LoadingUIPreview() {
    LoadingUi()
}
@Preview(showBackground = true)
@Composable
fun OfflineUIPreview() {
    OfflineUI(modifier = Modifier)
}