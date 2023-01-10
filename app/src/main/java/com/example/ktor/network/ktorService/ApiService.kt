package com.example.ktor.network.ktorService

import com.example.ktor.network.model.PostResponseItem
import com.example.ktor.utils.jsonDefaultInstance
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Inject

class ApiService @Inject constructor(){

    private val client = HttpClient(CIO){
        install(DefaultRequest){
            headers.append("Content-type","application/json")
        }

        install(ContentNegotiation) {
            json()
            jsonDefaultInstance


        }

        }

    suspend fun getPost(): ArrayList<PostResponseItem> {

        // val httpResponse: HttpResponse =

        return client.get("https://pravnapomosht.bg/wp-json/wp/v2/posts") {
            setBody{

            }
        }.body()

        // return val stringBody: String = httpResponse.body()

    }
}