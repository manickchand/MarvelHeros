package com.manickchand.marvelheros.heros

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.marvelheros.data.model.hero.CharacterReturn
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.network.IServiceRetrofit
import com.manickchand.marvelheros.data.network.RetrofitInit
import com.manickchand.marvelheros.data.util.API_PUBLIC_KEY
import com.manickchand.marvelheros.data.util.CHARACTER_LIMIT
import com.manickchand.marvelheros.data.util.TAG_DEBUC
import com.manickchand.marvelheros.data.util.getHash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HerosViewModel(private val iServiceRetrofit: IServiceRetrofit):ViewModel() {

    val herosLiveData: MutableLiveData<List<Hero>> = MutableLiveData()
    val hasErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getHeros(offset:Int){

        this.iServiceRetrofit.getAllEvents(
            CHARACTER_LIMIT,
            offset,
            ts,
            API_PUBLIC_KEY,
            getHash(ts.toString())
        ).enqueue(object: Callback<CharacterReturn>{

            override fun onFailure(call: Call<CharacterReturn>, t: Throwable) {
                Log.e(TAG_DEBUC,"[Error getHeros] "+t.message)
                hasErrorLiveData.value = true
            }
            override fun onResponse(
                call: Call<CharacterReturn>,
                response: Response<CharacterReturn>
            ) {

                if(response.isSuccessful){
                    hasErrorLiveData.value = false
                    val heros = response.body()?.data?.results ?: emptyList()
                    herosLiveData.value = heros
                }else{
                    Log.e(TAG_DEBUC,"[Response getHeros dont successful] code: "+response.code())
                    hasErrorLiveData.value = true
                }
            }
        })
    }

    companion object{
        val ts = System.currentTimeMillis()/1000
    }
}