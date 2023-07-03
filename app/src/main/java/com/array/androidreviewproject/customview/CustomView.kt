package com.array.androidreviewproject.customview

import android.animation.ValueAnimator
import android.content.Context
import android.nfc.Tag
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomView : View {

    companion object {
        const val TAG = "CustomView"
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun doAnimation() {
        var va = ValueAnimator.ofInt(10, 20, 50, 10)
        va.duration = 1000
        va.repeatCount = ValueAnimator.INFINITE
        va.repeatMode = ValueAnimator.REVERSE
        va.addUpdateListener { animation ->
            val curValue: Int = animation.animatedValue as Int
            Log.d(TAG, "$curValue")
        }
        va.start()
    }


}