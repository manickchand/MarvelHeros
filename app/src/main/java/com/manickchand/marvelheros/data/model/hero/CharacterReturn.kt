package com.manickchand.marvelheros.data.model.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CharacterReturn (
    var code:Int,
    var status:String,
    var etag:String,
    var data:DataReturn
)

data class DataReturn (
    var offset:Int,
    var limit:Int,
    var total:Int,
    var count:Int,
    var results:List<Hero>
)

//-----------------------------------------------
@Parcelize
data class Thumbnail (
    var path:String,
    var extension:String
): Parcelable

//--------------------------------------------------
@Parcelize
data class Comics (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemComics>,
    var returned:Int
):Parcelable

@Parcelize
data class ItemComics (
    var resourceURI:String? = "",
    var name:String? = ""
):Parcelable