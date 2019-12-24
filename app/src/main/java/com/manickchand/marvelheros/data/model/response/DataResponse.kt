package com.manickchand.marvelheros.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "results")
    val herosDetailResponses: List<HerosResponse>
)