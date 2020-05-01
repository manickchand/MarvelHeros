package com.manickchand.marvelheros.data.model.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero  (
    var id:Int?,
    var name:String,
    var description:String,
    var thumbnail:Thumbnail?,
    var comics:Comics?
    ): Parcelable