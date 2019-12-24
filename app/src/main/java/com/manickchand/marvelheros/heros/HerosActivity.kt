package com.manickchand.marvelheros.heros


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import kotlinx.android.synthetic.main.activity_heros.*

class HerosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heros)

        var viewModel =  ViewModelProviders.of(this).get(HerosViewModel::class.java)

        viewModel.herosLiveData.observe(this, Observer {
            it?.let { heros ->
                Log.i("Tag",heros.get(0).name)
                with(rv_heros){
                    layoutManager = LinearLayoutManager(this@HerosActivity, RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = HerosAdapter(this@HerosActivity,heros)
                }
            }
        })

        viewModel.getHeros()
    }
}
