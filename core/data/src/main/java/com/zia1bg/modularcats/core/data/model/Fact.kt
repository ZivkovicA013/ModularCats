package com.zia1bg.modularcats.core.data.model

import com.zia1bg.modularcats.core.network.dto.FactDto

data class Fact(val text: String, val length: Int)

fun FactDto.toFact(): Fact = Fact(this.fact, this.length)
