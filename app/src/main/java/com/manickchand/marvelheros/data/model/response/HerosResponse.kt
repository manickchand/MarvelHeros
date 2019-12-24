package com.manickchand.marvelheros.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HerosResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String

) {
    fun getHeroName() = this.name }