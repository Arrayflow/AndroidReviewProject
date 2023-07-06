package com.array.androidreviewproject.customview

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.addListener
import com.array.androidreviewproject.R

class CustomImageView : AppCompatImageView {

    private var mTop: Int = 0

    private var mCurImgIndex = 0

    private var mImgCount = 4

    private var customViewRepeatListener: CustomAnimationRepeatListener? = null

    var mValueAnimator: ValueAnimator

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleSet: Int) : super(
        context,
        attrs,
        defStyleSet
    )

    init {
        mValueAnimator = ValueAnimator.ofInt(0, 100, 0)
        mValueAnimator.repeatMode = ValueAnimator.RESTART
        mValueAnimator.repeatCount = ValueAnimator.INFINITE
        mValueAnimator.duration = 1000
        mValueAnimator.interpolator = AccelerateDecelerateInterpolator()

        mValueAnimator.addUpdateListener { animation ->
            val dx = animation.animatedValue as Int
            top = mTop - dx
        }

        mValueAnimator.addListener(onStart = { setImageDrawable(resources.getDrawable(R.mipmap.lion)) },
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

    mValueAnimator.start()
}

override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    mTop = top
}

fun setOnCustomRepeatListener(listener: CustomAnimationRepeatListener) {
    customViewRepeatListener = listener
}

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mValueAnimator.removeAllListeners()
        mValueAnimator.removeAllUpdateListeners()
    }
}

interface CustomViewRepeatListener {
    fun onRepeatFinish()
}