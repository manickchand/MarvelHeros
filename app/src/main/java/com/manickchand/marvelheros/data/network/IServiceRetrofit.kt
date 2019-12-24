package com.manickchand.marvelheros.data.network

import com.manickchand.marvelheros.data.model.hero.CharacterReturn
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IServiceRetrofit {
    @GET("characters?limit=10&ts=1577117029993&apikey=b3f14670dbac4fc1f8772b7f66f6b4d0&hash=0373aef49dfae2f363d84eb5c990d15a")
    fun getAllEvents(
//        @Query("limit") limit:Int = 10,
//        @Query("ts") ts:Long = 1577117029993 ,
//        @Query("apikey") apiKey: String = "b3f14670dbac4fc1f8772b7f66f6b4d0",
//        @Query("hash") hash: String = "0373aef49dfae2f363d84eb5c990d15a"
    ): Call<CharacterReturn>
}