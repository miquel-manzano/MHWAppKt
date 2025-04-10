package cat.itb.m78.exercices.mhwApp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.mhwApp.Weapon
import coil3.compose.AsyncImage



@Composable
fun WeaponsList(weaponsList: List<Weapon>, navigateToFocusWeapon:(String) -> Unit){
    var textSearch by remember { mutableStateOf("") }

    if(weaponsList.any()){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(textSearch,
                label = { Text("Search") },
                onValueChange = {
                    textSearch = it
                })
            LazyColumn(
                modifier = Modifier
                    .padding(15.dp)
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                items(weaponsList){ weapon ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        onClick = {
                            navigateToFocusWeapon(weapon.weaponId.toString())
                        },
                        modifier = Modifier
                            .size(width = 300.dp, height = 100.dp)
                    ){
                        Row {
                            AsyncImage(
                                model = weapon.weaponAssets?.weaponIcon ?: "image not found",
                                contentDescription = weapon.weaponName
                            )
                            Text(
                                text = weapon.weaponName,
                                modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Magenta)
        }
    }
}