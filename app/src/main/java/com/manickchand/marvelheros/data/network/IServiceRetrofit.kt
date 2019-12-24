package com.manickchand.marvelheros.data.network

import com.manickchand.marvelheros.data.model.hero.CharacterReturn
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IServiceRetrofit {
    @GET("characters")
    fun getAllEvents(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int,
        @Query("ts") ts:Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<CharacterReturn>
}