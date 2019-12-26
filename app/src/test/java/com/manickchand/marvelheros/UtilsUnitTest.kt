package com.manickchand.marvelheros

import android.os.Build
import android.widget.ImageView
import com.manickchand.marvelheros.data.util.getHash
import com.manickchand.marvelheros.data.util.getUrlImage
import com.manickchand.marvelheros.data.util.hasInternet
import com.manickchand.marvelheros.data.util.loadImageView
import com.manickchand.marvelheros.heros.HerosActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class UtilsUnitTest {

    private lateinit var activity: HerosActivity

    @Before
    fun setUp() {
        activity =  Robolectric.buildActivity(HerosActivity::class.java)
            .create()
            .visible()
            .get()
    }

    @Test
    fun getHash_test(){

        val ts = 1577378043859
        val hash = getHash(ts.toString())
        assertEquals("ddac21da48ef386e5e02e3bfdd153f55",hash)
    }

    @Test
    fun getHash_empty_ts_test(){

        val ts = ""
        val hash = getHash(ts)
        assertEquals("8ddb385f3fc3032fc12f3ebcb121bbe1",hash)
    }

    @Test
    fun getUrlImage_test(){

        val path = "image_path"
        val type = "large"
        val extension = "jpg"
        var url = getUrlImage(path,extension,type)

        assertEquals("image_path/large.jpg",url)
    }

    @Test
    fun getUrlImage_empty_strings_test(){

        val path = ""
        val type = ""
        val extension = ""
        var url = getUrlImage(path,extension,type)

        assertEquals("/.",url)
    }

    @Test
    fun hasInternet_test(){
        val r =hasInternet(activity.applicationContext)
        assertEquals(false,r)
    }
}
