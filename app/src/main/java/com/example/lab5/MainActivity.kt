package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab5.navigation.AppNavHost
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Lab5Theme {
                AppNavHost()
            }
        }
    }
}
