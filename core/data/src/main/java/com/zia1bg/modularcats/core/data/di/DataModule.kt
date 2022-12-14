package com.zia1bg.modularcats.core.data.di

import com.zia1bg.modularcats.core.data.repository.CatRepository
import com.zia1bg.modularcats.core.data.repository.CatRepositoryImpl
import com.zia1bg.modularcats.core.network.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Installs AppModule in the generated SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    //Tells Hilt how to provide instance of this type
    @Provides
    // @Singleton providers are only called once per SingletonComponent instance.
    @Singleton
    fun provideCatRepository(api: CatApi): CatRepository = CatRepositoryImpl(api)
}