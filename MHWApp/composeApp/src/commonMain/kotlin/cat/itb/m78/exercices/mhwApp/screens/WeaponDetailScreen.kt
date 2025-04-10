package cat.itb.m78.exercices.mhwApp.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.mhwApp.viewmodels.ApiDetailViewModel

@Composable
fun WeaponDetailScreen(id: String, navigateToWeaponsScreen: () -> Unit){
    val model = viewModel { ApiDetailViewModel(id) }

    val weapon = model.weaponDetail.value

    Text(weapon?.weaponName?: "name not found")
    Button(onClick = navigateToWeaponsScreen){
        Text("Back")
    }
}