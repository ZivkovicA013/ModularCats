package com.zia1bg.modularcats.core.network.dto

data class Payload(
    val current_page: Int,
    val `data`: List<BreedDto>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val linkDtos: List<LinkDto>,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)