package com.example.tutorthi.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorthi.repository.XeHoiRepository
import com.example.tutorthi.room.DBHelper
import com.example.tutorthi.screen.Home
import com.example.tutorthi.screen.Welcome
import com.example.tutorthi.viewModel.XeHoiViewModel

@Composable
fun NavApp(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = DBHelper.getIntance(context)
    val repository = XeHoiRepository(db)
    val viewModel = XeHoiViewModel(repository)

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route){ Welcome(navController) }
        composable(Screen.Home.route){ Home(navController,viewModel) }
    }
}