package com.example.ktor.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val rendered: String?=null
)