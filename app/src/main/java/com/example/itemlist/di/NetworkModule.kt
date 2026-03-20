package com.example.itemlist.di

import com.example.itemlist.data.network.ProductApiService
import com.example.itemlist.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ProductApiService {
        return RetrofitClient.api
    }

}