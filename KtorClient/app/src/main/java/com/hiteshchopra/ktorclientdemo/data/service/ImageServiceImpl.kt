package com.hiteshchopra.ktorclientdemo.data.service

import com.hiteshchopra.ktorclientdemo.data.HttpRoutes
import com.hiteshchopra.ktorclientdemo.data.model.ImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode

class ImageServiceImpl(
    private val client: HttpClient
) : ImageService {
    override suspend fun getImages(): ImageResponse {
        return client.get {

            url(HttpRoutes.FETCH_IMAGES_URL)
        }.body()
    }
}