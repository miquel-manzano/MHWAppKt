package cat.itb.m78.exercices.mhwApp.viewmodels

import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.p2.db.database

class DataBaseViewModel(): ViewModel() {
    private val FavWeaponsBD = database.favWeaponsQueries

}