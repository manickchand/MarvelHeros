package com.manickchand.marvelheros.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersBodyResponse(
    @Json(name = "data")
    val dataResults: DataResponse
)