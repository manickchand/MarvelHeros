package com.manickchand.marvelheros.heros

import android.os.Build
import com.manickchand.marvelheros.data.model.hero.Comics
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.model.hero.ItemComics
import com.manickchand.marvelheros.data.model.hero.Thumbnail
import kotlinx.android.synthetic.main.activity_heros.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class HerosActivityTest{

    private lateinit var activity: HerosActivity

    private val testHerosList = listOf(
        Hero(0,
            "Hulk",
            "Descricao Hulk",
            "",
            null,
            null,
            null,
            null,
            null,
            null,
            null
            ),
        Hero(1,
            "Homem Aranha",
            "Descricao Homem Aranha",
            "121212",
            Thumbnail("","jpg"),
            null,
            Comics(1,"", listOf(ItemComics()),1),
            null,
            null,
            null,
            null
        )
    )

    @Before
    fun setUp() {
        activity =  Robolectric.buildActivity(HerosActivity::class.java)
            .create()
            .visible()
            .get()
    }

    @Test
    @Throws(Exception::class)
    fun check_Activity_Not_Null() {
        assertNotNull(activity)
    }

    @Test
    @Throws(Exception::class)
    fun rv_Adapter_Size_Test() {

        val rv = activity.rv_heros
        rv.adapter = HerosAdapter(activity.applicationContext,testHerosList,{})
        val size = rv.adapter!!.itemCount
        assertEquals(testHerosList.size,size)
    }

    @Test
    @Throws(Exception::class)
    fun setup_RV_Test() {

        activity.mList = testHerosList as MutableList<Hero>
        activity.setupRecyclerView()
        var size = activity.rv_heros.adapter!!.itemCount
        assertEquals(testHerosList.size,size)
    }

    @Test
    fun con_Test() {

        activity.checkConnection()


    }

}

