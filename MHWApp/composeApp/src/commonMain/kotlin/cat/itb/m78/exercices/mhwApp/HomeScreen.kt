package cat.itb.m78.exercices.mhwApp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.mhwApp.screens.FavItemsList
import cat.itb.m78.exercices.mhwApp.screens.WeaponDetailScreen
import cat.itb.m78.exercices.mhwApp.screens.WeaponsList
import cat.itb.m78.exercices.mhwApp.viewmodels.ApiViewModel
import kotlinx.serialization.Serializable

object Screens{
    @Serializable
    data object WeaponsScreen
    @Serializable
    data class navigateToFocusWeapon (val id: String)
    @Serializable
    data object FavScreen
}


@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    val model = viewModel { ApiViewModel() }

    Scaffold(bottomBar = {
        NavigationBar{
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate(Screens.WeaponsScreen)},
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") }
            )
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate((Screens.FavScreen))},
                icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
                label = { Text("Favorites games") }
            )
        }
    }){
        NavHost(navController = navController, startDestination = Screens.WeaponsScreen) {
            composable<Screens.WeaponsScreen>{
                WeaponsList(
                    model.weaponsList.value, navigateToFocusWeapon = { navController.navigate(Screens.navigateToFocusWeapon(it)) }
                )
            }
            composable<Screens.navigateToFocusWeapon> { backStack ->
                val id = backStack.toRoute<Screens.navigateToFocusWeapon>().id
                WeaponDetailScreen(id, navigateToWeaponsScreen = { navController.navigate(Screens.WeaponsScreen) })
            }

            composable<Screens.FavScreen> {
                FavItemsList(
                    // Here Parameters
                )
            }
        }
    }
}