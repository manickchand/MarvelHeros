package com.manickchand.marvelheros.heros


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.util.CHARACTER_LIMIT
import com.manickchand.marvelheros.data.util.TAG_DEBUC
import kotlinx.android.synthetic.main.activity_heros.*

class HerosActivity : AppCompatActivity() {

    var offset = 0
    private var loading = false
    var pastVisiblesItems = 0
    var totalItemCount:Int = 0
    var mList:MutableList<Hero> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heros)

        val viewModel =  ViewModelProviders.of(this).get(HerosViewModel::class.java)

        with(rv_heros){

            layoutManager = LinearLayoutManager(this@HerosActivity, RecyclerView.VERTICAL,false)
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    if (dy > 0)
                    {
                        totalItemCount = layoutManager!!.itemCount
                        pastVisiblesItems = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!loading) {
                            if (pastVisiblesItems >= totalItemCount-1) {

                                loading = true
                                offset+= CHARACTER_LIMIT
                                viewModel.getHeros(offset)
                            }
                        }
                    }
                }
            })

            adapter = HerosAdapter(this@HerosActivity,mList)

        }

        viewModel.herosLiveData.observe(this, Observer {
            it?.let { heros ->
                mList.addAll(heros)
                rv_heros.adapter!!.notifyDataSetChanged()
                loading = false
            }
        })

        viewModel.getHeros(offset)
    }
}
