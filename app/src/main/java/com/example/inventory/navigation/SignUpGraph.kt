package com.example.inventory.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.login.ui.feature.login.LoginScreen
import com.example.login.ui.feature.login.LoginViewModel
import com.example.login.ui.feature.register.RegisterScreen
import com.example.login.ui.feature.register.RegisterViewModel


object SignUpGraph {
    const val ROUTE = "sign_up_graph"
    const val EMAIL = "email"
    const val PASSWORD = "password"

    // fun login(email: String = "", password: String = "") = "$ROUTE/login?$EMAIL=$email&$PASSWORD=$password"
    fun login() = "$ROUTE/login?$EMAIL={email}&$PASSWORD={password}"
    fun signUp() = "$ROUTE/signup"
}

fun NavGraphBuilder.signUpGraph(navController: NavController) {

    navigation(startDestination = SignUpGraph.login(), route = SignUpGraph.ROUTE) {
        login(navController)
        signUp(navController)
    }
}

private fun NavGraphBuilder.login(navController: NavController) {
    composable(
        route = SignUpGraph.login(),
        arguments = listOf(
            navArgument(SignUpGraph.EMAIL) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(SignUpGraph.PASSWORD) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString(SignUpGraph.EMAIL)?: ""
        val password = backStackEntry.arguments?.getString(SignUpGraph.PASSWORD)?: ""
        LoginScreen(
            email = email,
            password = password,
            hiltViewModel<LoginViewModel>(),
            goToAccountList = { navController.navigate(AccountGraph.accountList()){
                popUpTo(SignUpGraph.login()) { inclusive = true }
            } },
            goToSignUp = { navController.navigate(SignUpGraph.signUp()) },
        )
    }
}

private fun NavGraphBuilder.signUp(navController: NavController) {
    composable(route = SignUpGraph.signUp()) {
        val viewModel = hiltViewModel<RegisterViewModel>()
        RegisterScreen(
            goUp = { //email, password ->
                navController.navigate("sign_up_graph/login?email=${viewModel.state.email}&password=${viewModel.state.password}")},
            viewModel
        )

    }
}