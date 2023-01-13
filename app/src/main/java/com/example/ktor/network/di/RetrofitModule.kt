package com.example.ktor.network.di

import com.example.ktor.utils.baseUrl
import com.example.ktor.utils.jsonDefaultInstance
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*

import io.ktor.client.plugins.*
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)


object RetrofitModule {

    private val contentType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()


    @OptIn(ExperimentalSerializationApi::class)
    @Provides

    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(jsonDefaultInstance.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideWordpressApi(retrofit: Retrofit): WordpressApiRetro =
        retrofit.create(WordpressApiRetro::class.java)
}

