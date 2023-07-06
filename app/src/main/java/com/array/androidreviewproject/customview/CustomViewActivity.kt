package com.array.androidreviewproject.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.customview.evaluators.FallingBallEvaluator
import com.array.androidreviewproject.databinding.ActivityCustomViewBinding
import kotlin.math.cos
import kotlin.math.sin

class CustomViewActivity : BaseActivity<ActivityCustomViewBinding>() {

    var mCurPoint: Point = Point()

    var a: (Int) -> Boolean = {
        it == 1
    }

    var mIsMenuOpen: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pointCount = 0
        val handler = Handler(mainLooper)

        binding.customImageView.setOnCustomRepeatListener {
            pointCount = (pointCount + 1) % 3
            binding.customTv.text = when (pointCount) {
                0 -> "加载中."
                1 -> "加载中.."
                2 -> "加载中..."
                else -> "加载中"
            }
        }

        val ballAnimator = startBallMove()
        if (binding.imgBall.visibility == View.GONE) {
            binding.imgBall.visibility = View.VISIBLE
            ballAnimator.start()
        }

        binding.btnBall.setOnClickListener {
            ballAnimator.start()
            val objectAnimator = ObjectAnimator.ofFloat(binding.imgBall, "rotation", 0f, 180f, 360f)
            objectAnimator.duration = 5000
            objectAnimator.start()
        }

        binding.btnMenu.setOnClickListener {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true

            }
        }

    }

    override fun inflateViewBinding(): ActivityCustomViewBinding {
        return ActivityCustomViewBinding.inflate(layoutInflater)
    }

    fun startBallMove(): ValueAnimator {
        val animator = ValueAnimator.ofObject(FallingBallEvaluator(), Point(0, 0), Point(500, 500))
        animator.addUpdateListener { animation ->
            mCurPoint = animation.animatedValue as Point
            binding.imgBall.layout(
                mCurPoint.x,
                mCurPoint.y,
                mCurPoint.x + binding.imgBall.width,
                mCurPoint.y + binding.imgBall.height
            )
        }
        animator.duration = 3000
        return animator
    }

    fun okk(): ValueAnimator {
        val ani = ValueAnimator.ofObject(TypeEvaluator<Point> { fraction, startValue, endValue ->
            val mPoint = Point()
            mPoint.x = (startValue.x + fraction * (endValue.x - startValue.x)).toInt()
            mPoint.y = if (fraction <= 0.5f) {
                (startValue.y + (fraction * 2 * (endValue.y - startValue.y))).toInt()
            } else {
                endValue.y
            }
            return@TypeEvaluator mPoint
        }, Point(0, 0), Point(500, 500))
        return ani
    }

    fun openMenu() {

    }

    fun doAnimateOpen(view: View, index: Int, total: Int, radius: Int) {
        if (view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
        }
        val degree = Math.toRadians(90.0) / (total - 1) * index
        val translationX = -(radius * sin(degree)).toInt()
        val translationY = -(radius * cos(degree)).toInt()
        val set = AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(view, "translationX", 0f, translationX.toFloat()),
            ObjectAnimator.ofFloat(view, "translationY", 0f, translationY.toFloat()),
            ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f)
        )
    }
}