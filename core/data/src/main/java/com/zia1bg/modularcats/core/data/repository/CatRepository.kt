package com.zia1bg.modularcats.core.data.repository

import com.zia1bg.modularcats.core.data.model.Breed
import com.zia1bg.modularcats.core.data.model.Fact
import com.zia1bg.modularcats.core.network.dto.FactDto

interface CatRepository {

    suspend fun getFact(): Fact

    suspend fun getBreeds() : List<Breed>
}