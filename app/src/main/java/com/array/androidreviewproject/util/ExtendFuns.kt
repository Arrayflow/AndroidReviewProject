package com.array.androidreviewproject.util

import android.content.Context
import android.content.Intent

class ExtendFuns {

}

inline fun <reified T> go2Activity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}