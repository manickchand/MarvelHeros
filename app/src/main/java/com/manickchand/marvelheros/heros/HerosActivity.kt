package com.manickchand.marvelheros.heros


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.util.CHARACTER_LIMIT
import com.manickchand.marvelheros.data.util.hasInternet
import com.manickchand.marvelheros.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_heros.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HerosActivity : AppCompatActivity() {

    private val viewModel by viewModel<HerosViewModel>()
    private var offset = 0
    private var loading = false
    private var pastVisiblesItems = 0
    private var totalItemCount:Int = 0
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
//            swiperefresh.isRefreshing = false
            //changeLayout(error)
        })

        btn_try_again.setOnClickListener { checkConnection()}

//        swiperefresh.setColorSchemeResources(R.color.colorAccent)
//        swiperefresh.setOnRefreshListener{
//            this.checkConnection()
//        }

        checkConnection()
    }

    fun setupRecyclerView(){

        with(rv_heros){

            layoutManager = LinearLayoutManager(this@HerosActivity, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView, dx: Int, dy: Int
                ) {
                    if (dy > 0)
                    {
                        totalItemCount = layoutManager!!.itemCount
                        pastVisiblesItems = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!loading) {
                            if (pastVisiblesItems >= totalItemCount-1) {

                                loading = true
                                offset+= CHARACTER_LIMIT
                                checkConnection()
                            }
                        }
                    }
                }
            })

            adapter = HerosAdapter(this@HerosActivity, mList){ hero ->
                val intent = DetailsActivity.getStartIntent(this@HerosActivity, hero)
                this@HerosActivity.startActivity(intent)
            }
        }
    }

    fun checkConnection(){
        if(hasInternet(this)){
//            swiperefresh.isRefreshing = true
            viewModel.getHeros(offset)
            //changeLayout(false)
        }else{
            //changeLayout(true)
        }
    }

//    fun changeLayout(error: Boolean) {
//        if(error){
//            swiperefresh.visibility = View.GONE
//            ll_error.visibility = View.VISIBLE
//        }
//        else{
//            swiperefresh.visibility = View.VISIBLE
//            ll_error.visibility = View.GONE
//        }
//    }
}
