package com.zia1bg.modularcats.core.data.model

import com.zia1bg.modularcats.core.network.dto.BreedDto

data class Breed(
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
)

fun BreedDto.toBreed(): Breed =
    Breed(this.breed, this.country, this.origin, this.coat, this.pattern)


