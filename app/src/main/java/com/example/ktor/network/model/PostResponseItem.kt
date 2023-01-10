package com.example.ktor.network.model

import kotlinx.serialization.Serializable


@Serializable
data class PostResponseItem(
    val body: String?=null,
    val userId: Int?=0,
    val _embedded: Embedded,
    val content: Content,
    val date: String?=null,
    val id: Int?=0,
    val title: Title
)