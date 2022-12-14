package com.zia1bg.modularcats.core.network.di

import com.zia1bg.modularcats.core.network.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Installs AppModule in the generated SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://catfact.ninja/"

    //Tells Hilt how to provide instance of this type
    @Provides
    // @Singleton providers are only called once per SingletonComponent instance.
    @Singleton
    fun provideCatApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApi::class.java)
    }
}