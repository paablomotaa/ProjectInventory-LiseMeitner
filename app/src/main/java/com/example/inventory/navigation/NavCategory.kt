
package app.domain.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.categorycreation.ui.creation.CategoryCreateViewModel
import app.features.categorycreation.ui.creation.CategoryFormScreen
import app.features.categorylist.ui.CategoryListScreen
import app.features.categorylist.ui.CategoryListViewModel



object CategoryGraph {
    const val ROUTE = "CategoryGraph"
    fun categoryList() = "$ROUTE/categorylist"
    fun categoryCreate() = "$ROUTE/categorycreate"
}

fun NavGraphBuilder.categoryGraph(
    navController: NavController,
    categoryListViewModel: CategoryListViewModel,
    categoryCreateViewModel: CategoryCreateViewModel
) {
    navigation(startDestination = CategoryGraph.categoryList(), route = CategoryGraph.ROUTE) {
        categoryList(navController, categoryListViewModel)
        categoryCreate(navController, categoryCreateViewModel)
    }
}

private fun NavGraphBuilder.categoryList(navController: NavController, categoryListViewModel: CategoryListViewModel) {
    composable(route = CategoryGraph.categoryList()) {
        CategoryListScreen(
            goAdd = { navController.navigate(CategoryGraph.categoryCreate())}, categoryListViewModel)

    }
}


private fun NavGraphBuilder.categoryCreate(
    navController: NavController,
    categoryCreateViewModel: CategoryCreateViewModel
) {
    composable(route = CategoryGraph.categoryCreate()) {
        CategoryFormScreen(
            goBack = {navController.popBackStack()},
            viewModel = categoryCreateViewModel,
            navController = navController
        )
    }
}
