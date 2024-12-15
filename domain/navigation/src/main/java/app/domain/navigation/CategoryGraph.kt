package app.domain.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.features.categorycreation.ui.creation.CategoryFormScreen
import app.features.categorylist.ui.CategoryListScreen
import app.features.categorylist.ui.CategoryListViewModel


object CategoryGraph {
    const val ROUTE = "category_graph"
    fun categoryList() = "$ROUTE/CategoryList"
    fun categoryCreate() = "$ROUTE/CategoryCreate"
}

fun NavGraphBuilder.categoryGraph(navController: NavController) {
    navigation(startDestination = CategoryGraph.categoryList(), route = CategoryGraph.ROUTE) {
        categoryList(navController)
        categoryCreate(navController)
    }
}

private fun NavGraphBuilder.categoryList(navController: NavController) {
    composable(route = CategoryGraph.categoryList()) {
        CategoryListScreen(
            goToCategoryCreate = { navController.navigate(CategoryGraph.categoryCreate()) }
        )
    }
}

private fun NavGraphBuilder.categoryCreate(navController: NavController) {
    composable(route = CategoryGraph.categoryCreate()) {
        CategoryFormScreen(
            goBack = { navController.popBackStack() }
        )
    }
}