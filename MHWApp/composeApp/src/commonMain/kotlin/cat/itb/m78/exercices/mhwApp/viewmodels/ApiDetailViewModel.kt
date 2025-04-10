package cat.itb.m78.exercices.mhwApp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.mhwApp.ApiMHW
import cat.itb.m78.exercices.mhwApp.WeaponDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiDetailViewModel(id: String) : ViewModel() {
    val weaponDetail = mutableStateOf<WeaponDetail?>(null)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            weaponDetail.value = ApiMHW.detail(id)
        }
    }
}