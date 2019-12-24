package com.manickchand.marvelheros.heros

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.marvelheros.data.model.hero.CharacterReturn
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.network.RetrofitInit
import com.manickchand.marvelheros.data.util.API_PUBLIC_KEY
import com.manickchand.marvelheros.data.util.CHARACTER_LIMIT
import com.manickchand.marvelheros.data.util.TAG_DEBUC
import com.manickchand.marvelheros.data.util.getHash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HerosViewModel:ViewModel() {
    val herosLiveData: MutableLiveData<List<Hero>> = MutableLiveData()

    fun getHeros(offset:Int){
        RetrofitInit.service.getAllEvents(CHARACTER_LIMIT, offset, ts, API_PUBLIC_KEY, getHash(ts.toString())).enqueue(object: Callback<CharacterReturn>{

            override fun onFailure(call: Call<CharacterReturn>, t: Throwable) {
               Log.i(TAG_DEBUC,"Error Retrofit: "+t.message)
            }

            override fun onResponse(
                call: Call<CharacterReturn>,
                response: Response<CharacterReturn>
            ) {
                val heros = response.body()!!.data.results

                herosLiveData.value = heros
                //todo verificar status da requisicao

            }

        })
    }

    companion object{
        val ts = System.currentTimeMillis()/1000
    }
}