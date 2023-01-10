package com.example.ktor.network.ktorService

import android.util.Log
import com.example.ktor.network.model.PostResponseItem
import com.example.ktor.utils.jsonDefaultInstance
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.status
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import javax.inject.Inject

class ApiService @Inject constructor() {


//    private val client = HttpClient(OkHttp) {
//        install(DefaultRequest) {
//            headers.append("Content-type", "application/json")
//        }

//        install(ContentNegotiation) {
//            jsonDefaultInstance
//            json()
//        }
//
//        engine {
//            config {
//               // followRedirects(true)
//                Log.d("HTTP status:", "${status?.value}")
//
//            }
//        }
//    }

    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            headers.append("Content-type", "application/json")
        }

        install(ContentNegotiation) {
            // json(jsonDefaultInstance)
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

        }
        install(Logging) {
            logger = object : Logger {

                override fun log(message: String) {
                    Log.v("Logger ktor works", message)
                }

            }
            level = LogLevel.ALL
        }
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }
    }

    suspend fun getPost(): ArrayList<PostResponseItem> {
        // val httpResponse: HttpResponse =
        //https://pravnapomosht.bg/wp-json/wp/v2/posts
        //https://jsonplaceholder.typicode.com/posts
        return client.get("https://pravnapomosht.bg/wp-json/wp/v2/posts").body()
        // return val stringBody: String = httpResponse.body()

    }
}