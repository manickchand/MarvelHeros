package com.manickchand.marvelheros.heros

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.marvelheros.data.model.hero.CharacterReturn
import com.manickchand.marvelheros.data.model.response.CharactersBodyResponse
import com.manickchand.marvelheros.data.network.RetrofitInit
import com.manickchand.marvelheros.data.util.TAG_DEBUC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HerosViewModel:ViewModel() {
    val herosLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun getHeros(){
        //herosLiveData.value = getFakeHeros()
        RetrofitInit.service.getAllEvents().enqueue(object: Callback<CharacterReturn>{
            override fun onFailure(call: Call<CharacterReturn>, t: Throwable) {
               Log.i(TAG_DEBUC,"Error Retrofit: "+t.message)
            }

            override fun onResponse(
                call: Call<CharacterReturn>,
                response: Response<CharacterReturn>
            ) {
                val res = response.body()!!
                //todo verificar status da requisicao
                Log.i(TAG_DEBUC,"success : "+res)
            }

        })
    }

    fun getFakeHeros():List<String>{
        return listOf("Leon", "Beth", "Belly")
    }
}