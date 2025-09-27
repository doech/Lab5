package com.example.lab5.navigation

sealed class Routes(val route: String) {
    data object List : Routes("list")
    data object Detail : Routes("detail/{name}") {
        fun create(name: String) = "detail/$name"
    }
}
