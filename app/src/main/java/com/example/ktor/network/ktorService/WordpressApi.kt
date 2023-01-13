package com.example.ktor.network.ktorService

import android.util.Log
import com.example.ktor.data.PostsModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WordpressApi @Inject constructor() {


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

    suspend fun getWpApi(): List<PostsModel> {


        // val httpResponse: HttpResponse =
        //https://pravnapomosht.bg/wp-json/wp/v2/posts
        return client.get("https://pravnapomosht.bg/wp-json/wp/v2/posts") {
//            setBody{
//                url {
//                    parameters.append("categories" , toString() )
//                }
//            }
        }
            .body()
        // return val stringBody: String = httpResponse.body()
    }
//    suspend fun getPostByCat():  List<PagingData> {
//
//        return client.get("https://pravnapomosht.bg/wp-json/wp/v2/posts") {
//            setBody{
//                url {
//
//                    parameters.append("categories",  "per_page")
//                }
//            }
//        }.body()
//    }
}