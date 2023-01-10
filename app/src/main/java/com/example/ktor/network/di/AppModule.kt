package com.example.ktor.network.di

import android.app.Application
import androidx.room.Room
import com.example.ktor.network.model.room.RestaurantDatabase
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

    fun provideDatabase(app: Application) : RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "Restaurant Database")
            .build()



}