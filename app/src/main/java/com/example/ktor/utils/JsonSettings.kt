package com.example.ktor.utils

import kotlinx.serialization.json.Json

val jsonDefaultInstance =
    Json { ignoreUnknownKeys = true; isLenient = true; encodeDefaults = false; }
    const val baseUrl = "https://pravnapomosht.bg/"