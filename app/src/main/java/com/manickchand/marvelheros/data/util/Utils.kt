package com.manickchand.marvelheros.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import com.manickchand.marvelheros.R
import com.squareup.picasso.Picasso
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun getUrlImage(path:String, extension:String, type:String ):String{
    return "$path/$type.$extension"
}

fun getHash(ts: String): String {
    try {

        val md = MessageDigest.getInstance(MD5)

        val messageDigest = md.digest(
            ts.toByteArray()
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

fun hasInternet(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val network = connectivityManager?.activeNetwork
        val connection = connectivityManager?.getNetworkCapabilities(network)
        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    } else {
        val activeNetwork = connectivityManager?.activeNetworkInfo
        if (activeNetwork != null) {
            return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }
}

fun loadImageView(image: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(image)
}
