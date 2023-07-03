package com.array.androidreviewproject.customview

import android.animation.Animator
import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.addListener
import com.array.androidreviewproject.R

class CustomImageView : AppCompatImageView {

    private var mTop: Int = 0

    private var mCurImgIndex = 0

    private var mImgCount = 4

    private var customViewRepeatListener: CustomViewRepeatListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleSet: Int) : super(
        context,
        attrs,
        defStyleSet
    )

    init {
        val valueAnimator = ValueAnimator.ofInt(0, 100, 0)
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 1000
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()

        valueAnimator.addUpdateListener { animation ->
            val dx = animation.animatedValue as Int
            top = mTop - dx
        }

        valueAnimator.addListener(onStart = { setImageDrawable(resources.getDrawable(R.mipmap.lion)) },
            onCancel = {}, onEnd = {}
        ) {
            customViewRepeatListener?.onRepeatFinish()
            mCurImgIndex = (mCurImgIndex + 1) % mImgCount
            when (mCurImgIndex) {
                0 -> setImageDrawable(resources.getDrawable(R.mipmap.lion))
                1 -> setImageDrawable(resources.getDrawable(R.mipmap.monkey))
                2 -> setImageDrawable(resources.getDrawable(R.mipmap.tiger))
                3 -> setImageDrawable(resources.getDrawable(R.mipmap.cola))
            }
        }


//        valueAnimator.addListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(animation: Animator?) {
//                setImageDrawable(resources.getDrawable(R.mipmap.lion))
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
//
//            }
//
//            override fun onAnimationCancel(animation: Animator?) {
//
//            }
//
//            override fun onAnimationRepeat(animation: Animator?) {
//                customViewRepeatListener.onRepeatFinish()
//                mCurImgIndex = (mCurImgIndex + 1) % mImgCount
//                when (mCurImgIndex) {
//                    0 -> setImageDrawable(resources.getDrawable(R.mipmap.lion))
//                    1 -> setImageDrawable(resources.getDrawable(R.mipmap.monkey))
//                    2 -> setImageDrawable(resources.getDrawable(R.mipmap.tiger))
//                    3 -> setImageDrawable(resources.getDrawable(R.mipmap.cola))
//                }
//            }
//        })

    valueAnimator.start()
}

override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    mTop = top
}

fun setOnCustomViewRepeatListener(listener: CustomViewRepeatListener) {
    customViewRepeatListener = listener
}

}

interface CustomViewRepeatListener {
    fun onRepeatFinish()
}