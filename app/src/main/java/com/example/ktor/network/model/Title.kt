package com.example.ktor.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Title(
    val rendered: String?=null
)