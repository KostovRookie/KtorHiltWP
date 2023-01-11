package com.example.ktor.network.di

import android.app.Application
import androidx.room.Room
import com.example.ktor.data.PravoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton

    fun provideDatabase(app: Application) : PravoDatabase =
        Room.databaseBuilder(app, PravoDatabase::class.java, "Stupid Database")
            .build()



}