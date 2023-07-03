package com.array.androidreviewproject.customview

import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.customview.evaluators.FallingBallEvaluator
import com.array.androidreviewproject.databinding.ActivityCustomViewBinding

class CustomViewActivity : BaseActivity<ActivityCustomViewBinding>() {

    var mCurPoint: Point = Point()

    var a: (Int) -> Boolean = {
        it == 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pointCount = 0
        val handler = Handler(mainLooper)
        binding.customImageView.setOnCustomViewRepeatListener(object : CustomViewRepeatListener {
            override fun onRepeatFinish() {
                pointCount = (pointCount + 1) % 3
                binding.customTv.text = when (pointCount) {
                    0 -> "加载中."
                    1 -> "加载中.."
                    2 -> "加载中..."
                    else -> "加载中"
                }
            }
        })

        val ballAnimator = startBallMove()
        ballAnimator.start()

        binding.btnBall.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ballAnimator.start()
            }
        })

    }

    override fun inflateViewBinding(): ActivityCustomViewBinding {
        return ActivityCustomViewBinding.inflate(layoutInflater)
    }

    fun startBallMove(): ValueAnimator {
        val animator = ValueAnimator.ofObject(FallingBallEvaluator(), Point(0, 0), Point(500, 500))
        animator.addUpdateListener { animation ->
            mCurPoint = animation.animatedValue as Point
            binding.imgBall.layout(mCurPoint.x, mCurPoint.y, mCurPoint.x + binding.imgBall.width, mCurPoint.y + binding.imgBall.height)
        }
        animator.duration = 3000
        return animator
    }



}