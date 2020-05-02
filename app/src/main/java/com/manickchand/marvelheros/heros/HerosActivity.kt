package com.manickchand.marvelheros.heros


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.util.hasInternet
import com.manickchand.marvelheros.data.util.showToast
import com.manickchand.marvelheros.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_heros.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HerosActivity : AppCompatActivity() {

    private val viewModel by viewModel<HerosViewModel>()
    private var offset = 0
    private var loading = false
    private var mList:MutableList<Hero> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heros)

        setupRecyclerView()

        viewModel.herosLiveData.observe(this, Observer {
            it?.let { heros ->
                mList.addAll(heros)
                rv_heros.adapter!!.notifyDataSetChanged()
                loading = false
            }
        })

        viewModel.hasErrorLiveData.observe(this, Observer {error ->
            if(error) showToast("getHeros error")
        })

        checkConnection()
    }

    fun setupRecyclerView(){

        with(rv_heros){
            layoutManager = LinearLayoutManager(this@HerosActivity, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)

            adapter = HerosAdapter(this@HerosActivity, mList){ hero ->
                val intent = DetailsActivity.getStartIntent(this@HerosActivity, hero)
                this@HerosActivity.startActivity(intent)
            }
        }
    }

    fun checkConnection(){
        if(hasInternet(this)){
            viewModel.getHeros(offset)
        }else{
            showToast("Internet error")
        }
    }

}
