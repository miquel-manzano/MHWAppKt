package cat.itb.m78.exercices.mhwApp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Weapon(
    @SerialName("id") val weaponId: Int,
    @SerialName("name") val weaponName: String,
    @SerialName("assets") val weaponAssets: Assets? = null
){
    @Serializable
    data class Assets(
        @SerialName("icon") val weaponIcon: String? = null,
        @SerialName("image") val weaponImage: String? = null
    )
}

@Serializable
data class WeaponDetail(
    @SerialName("id") val weaponId: Int,
    @SerialName("type") val weaponType: String,
    @SerialName("rarity") val weaponRarity: Int,
    @SerialName("damageType") val weaponDamageType: String,
    @SerialName("name") val weaponName: String,
    @SerialName("assets") val weaponAssets: Assets
){
    @Serializable
    data class Assets(
        @SerialName("icon") val weaponIcon: String? = null,
        @SerialName("image") val weaponImage: String? = null
    )
}


object ApiMHW {
    private const val URL = "https://mhw-db.com/weapons/"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(URL).body<List<Weapon>>()
    suspend fun detail(id: String) = client.get(URL + id).body<WeaponDetail>()
}