package com.zia1bg.modularcats.core.data.repository

import com.zia1bg.modularcats.core.data.model.Fact
import com.zia1bg.modularcats.core.data.model.toFact
import com.zia1bg.modularcats.core.network.CatApi
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val api: CatApi) : CatRepository {


    override suspend fun getFact(): Fact = api.getFact(100).toFact()
    // TODO: Move MAX_LENGTH in common util file or etc

    override suspend fun getBreeds() {
        TODO("Not yet implemented")
    }
}