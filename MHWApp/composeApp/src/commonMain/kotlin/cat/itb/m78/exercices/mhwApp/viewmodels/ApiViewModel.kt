package cat.itb.m78.exercices.mhwApp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.mhwApp.ApiMHW
import cat.itb.m78.exercices.mhwApp.Weapon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel() {
    var weaponsList = mutableStateOf(listOf<Weapon>())
    init {
        viewModelScope.launch(Dispatchers.Default) {
            weaponsList.value = ApiMHW.list()
        }
    }
}