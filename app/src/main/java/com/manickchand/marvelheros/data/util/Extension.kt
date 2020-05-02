package com.manickchand.marvelheros.data.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showToast(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()