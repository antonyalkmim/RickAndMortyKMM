import com.example.rickandmortykmm.Models.Character
import com.example.rickandmortykmm.Models.PaginatedCharacters
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.network.tls.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.logging.*
import kotlinx.serialization.json.Json

class RickAndMortyService {

    private val httpClient = HttpClient() {
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }
        install(Logging)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun listCharacters(): List<Character> {
        return httpClient.get("character")
            .body<PaginatedCharacters>()
            .results
    }
}