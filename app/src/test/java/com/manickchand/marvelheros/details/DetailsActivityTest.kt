package com.manickchand.marvelheros.details

import android.os.Build
import com.manickchand.marvelheros.data.model.hero.Hero
import com.manickchand.marvelheros.data.model.hero.ItemComics
import kotlinx.android.synthetic.main.activity_details.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class DetailsActivityTest{

    private lateinit var activity: DetailsActivity

    private val testHero = Hero(0,
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
        )

    private val listItemHeroComics = listOf(
        ItemComics("1","Comics 1"),
        ItemComics("2","Comics 2"),
        ItemComics("3","Comics 3"))

    @Before
    fun setUp() {
        activity =  Robolectric.buildActivity(DetailsActivity::class.java)
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
    fun receiveHero_test() {
        activity.receiveHero(testHero)
        assertEquals(testHero.name, activity.tv_name_detail.text)
        assertEquals(testHero.description, activity.tv_description_detail.text)
    }

    @Test
    @Throws(Exception::class)
    fun receiveHero_Null_test() {
        activity.receiveHero(null)
        assertTrue(activity.isFinishing)
    }

    @Test
    fun detailsAdapter_List_Empty_test() {
        val rv = activity.rv_comics
        rv.adapter = DetailsAdapter(activity.applicationContext, emptyList())

        val size = rv.adapter!!.itemCount
        assertEquals(0,size)
    }

    @Test
    fun detailsAdapter_Size_test() {

        val rv = activity.rv_comics
        rv.adapter = DetailsAdapter(activity.applicationContext, listItemHeroComics)

        val size = rv.adapter!!.itemCount
        assertEquals(listItemHeroComics.size, size)
    }

}