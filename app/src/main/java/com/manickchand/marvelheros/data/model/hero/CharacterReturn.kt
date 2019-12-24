package com.manickchand.marvelheros.data.model.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CharacterReturn (
    var code:Int,
    var status:String,
    var copyright:String,
    var attributionText:String,
    var attributionHTML:String,
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
    var items:List<ItemStories>,//todo varificar
    var returned:Int
):Parcelable

@Parcelize
data class ItemComics (
    var resourceURI:String? = "",
    var name:String? = ""
):Parcelable

//----------------------------------------------------
@Parcelize
data class Series (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemSeries>,
    var returned:Int
):Parcelable

@Parcelize
data class ItemSeries (
    var resourceURI:String,
    var name:String?
):Parcelable

// -------------------------------------------------
@Parcelize
data class Stories (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemStories>,
    var returned:Int
):Parcelable

@Parcelize
data class ItemStories (
    var resourceURI:String,
    var name:String,
    var type:String
):Parcelable

//-----------------------------------------------------
@Parcelize
data class Events (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemEvents>,
    var returned:Int
):Parcelable

@Parcelize
data class ItemEvents (
    var resourceURI:String,
    var name:String
):Parcelable

@Parcelize
data class ItemUrls (
    var type:String,
    var url:String
):Parcelable