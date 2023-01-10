package com.example.ktor.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WpTerm(
val id: Int?=0,
val name: String?=null
)
