package com.manickchand.marvelheros.data.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun getUrlImage(path:String, extension:String ):String{
    return "$path/landscape_xlarge.$extension"
}

fun getHash(ts: String): String {
    try {

        val md = MessageDigest.getInstance("MD5")

        val messageDigest = md.digest(ts.toByteArray()
                + API_PRIVATE_KEY.toByteArray()
                + API_PUBLIC_KEY.toByteArray())

        val no = BigInteger(1, messageDigest)

        var hashtext = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}
