package mm.kittensdev.project

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.http.HttpHeaders
import kotlin.io.encoding.*


@Serializable
data class SpotifyTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String? = null
)

@Serializable
data class SpotifyTrack(val name: String, val artists: List<Artist>)

@Serializable
data class Artist(val name: String)

class SpotifyApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }

    private var accessToken: String? = null

    // Para autenticación (necesitarás implementar el servidor o usar PKCE)
    suspend fun authenticate(clientId: String, clientSecret: String): Boolean {
        // Implementa el flujo de autenticación según tus necesidades
        // Esto es un ejemplo simplificado
        val response: SpotifyTokenResponse = client.post("https://accounts.spotify.com/api/token") {
            header(HttpHeaders.Authorization, "Basic ${base64Encode("$clientId:$clientSecret")}")
            formData {
                append("grant_type", "client_credentials")
            }
        }.body()

        accessToken = response.access_token
        return accessToken != null
    }

    // Ejemplo de petición para buscar tracks
    suspend fun searchTracks(query: String): List<SpotifyTrack> {
        if (accessToken == null) throw IllegalStateException("No autenticado")

        val response = client.get("https://api.spotify.com/v1/search") {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            parameter("q", query)
            parameter("type", "track")
            parameter("limit", "10")
        }

        // Necesitarás crear clases de modelo para la respuesta completa
        // Esta es una simplificación
        return response.body<Map<String, Any>>()["tracks"]?.items ?: emptyList()
    }

    private fun base64Encode(text: String): String {
        return text.encodeToByteArray().toBase64()
    }
}