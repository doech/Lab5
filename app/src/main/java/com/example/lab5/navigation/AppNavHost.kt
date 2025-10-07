package com.example.lab5.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab5.ui.detail.PokemonDetailScreen
import com.example.lab5.ui.detail.PokemonDetailViewModel
import com.example.lab5.ui.list.PokemonListScreen
import com.example.lab5.ui.list.PokemonListViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.List.route) {

        composable(Routes.List.route) {
            val vm: PokemonListViewModel = viewModel()
            PokemonListScreen(
                vm = vm,
                onOpenDetail = { name -> navController.navigate(Routes.Detail.create(name)) }
            )
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")!!
            val vm: PokemonDetailViewModel = viewModel()
            PokemonDetailScreen(nameOrId = name, vm = vm, onBack = { navController.navigateUp() })
        }
    }
}
