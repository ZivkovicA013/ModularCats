package com.zia1bg.modularcats.core.network

import com.zia1bg.modularcats.core.network.dto.FactDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("fact")
    suspend fun getFact(@Query("max_length") max_length: Int): FactDto

    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int): Any
}