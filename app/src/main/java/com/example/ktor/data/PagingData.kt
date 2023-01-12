package com.example.ktor.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PagingData (
    @SerialName("categories") val categories:Int,
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("_embed") val embed: Boolean
)