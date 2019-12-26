package com.manickchand.marvelheros.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.util.getUrlImage
import com.manickchand.marvelheros.data.util.loadImageView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var hero:Hero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        hero = intent.getParcelableExtra(EXTRA_HERO)

        if(hero!=null){
            setData()
        }else{
            Toast.makeText(this, R.string.error_details, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun setData(){
        val urlImg = getUrlImage(hero?.thumbnail!!.path, hero?.thumbnail!!.extension)
        loadImageView(iv_hero_detail,urlImg)

        tv_name_detail.text = hero?.name
        tv_description_detail.text = hero?.description

        with(rv_comics){
            layoutManager = LinearLayoutManager(this@DetailsActivity, RecyclerView.VERTICAL,false)
            setHasFixedSize(true)
            adapter = DetailsAdapter(this@DetailsActivity, hero?.comics?.items!!)
        }
    }

    companion object {
        private const val EXTRA_HERO = "EXTRA_HERO"

        fun getStartIntent(context: Context, hero: Hero): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_HERO, hero)
            }
        }
    }
}
