package com.hollow.imbdfilmes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hollow.imbdfilmes.ui.MovieDetailScreen
import com.hollow.imbdfilmes.ui.MovieSearchScreen

object Destinations {
    const val SEARCH_SCREEN = "search"
    const val DETAIL_SCREEN = "detail/{imdbId}"
    fun detailRoute(imdbId: String) = "detail/$imdbId"
}

@Composable
fun AppNavigation(viewModel: MovieViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.SEARCH_SCREEN) {
        composable(Destinations.SEARCH_SCREEN) {
            MovieSearchScreen(
                viewModel = viewModel,
                onMovieClick = { imdbId ->
                    navController.navigate(Destinations.detailRoute(imdbId))
                }
            )
        }
        composable(
            route = Destinations.DETAIL_SCREEN,
            arguments = listOf(navArgument("imdbId") { type = NavType.StringType })
        ) { backStackEntry ->
            val imdbId = backStackEntry.arguments?.getString("imdbId")
            requireNotNull(imdbId) { "imdbId não pode ser nulo" }
            MovieDetailScreen(
                imdbId = imdbId,
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                    viewModel.clearMovieDetails()
                }
            )
        }
    }
}
