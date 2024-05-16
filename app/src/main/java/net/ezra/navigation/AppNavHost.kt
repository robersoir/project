package net.ezra.navigation

import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.MainViewModel
import net.ezra.ui.SplashScreen
import net.ezra.ui.about.AboutScreen
import net.ezra.ui.auth.LoginScreen
import net.ezra.ui.auth.SignUpScreen
import net.ezra.ui.dashboard.DashboardScreen
//import net.ezra.ui.auth.SignupScreen
import net.ezra.ui.products.AddProductScreen
import net.ezra.ui.products.ProductDetailScreen
import net.ezra.ui.products.ProductListScreen
import net.ezra.ui.start.StartScreen
import net.ezra.ui.students.AddStudents
import net.ezra.ui.students.Search
import net.ezra.ui.students.Students



@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    mainViewModel: MainViewModel


) {


    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {




        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }


        composable(ROUTE_ADD_STUDENTS) {
            AddStudents(navController)
        }

        composable(ROUTE_SPLASH) {
            SplashScreen(navController , context = this@NavHost )
        }

        composable(ROUTE_VIEW_STUDENTS) {
           Students(navController = navController, viewModel = viewModel() )
        }

        composable(ROUTE_SEARCH) {
            Search(navController)
        }

        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUTE_REGISTER) {
           SignUpScreen(navController = navController) {

           }
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController){}
        }

        composable(ROUTE_ADD_PRODUCT) {
            AddProductScreen(navController = navController){}
        }

        composable(ROUTE_VIEW_PROD) {
            ProductListScreen(navController = navController, products = listOf() )
        }


        composable(ROUTE_START) {
            StartScreen(navController = navController)
        }



        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }









































    }
}