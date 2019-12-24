package com.manickchand.marvelheros.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.manickchand.marvelheros.R
import com.manickchand.marvelheros.data.model.hero.Hero
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
