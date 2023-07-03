package com.array.androidreviewproject.customview.evaluators

import android.animation.TypeEvaluator
import android.graphics.Point

class FallingBallEvaluator : TypeEvaluator<Point> {
    private var point: Point = Point()

    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        point.x = (startValue.x + fraction * (endValue.x - startValue.x)).toInt()
        point.y = if (fraction <= 0.5f) {
            (startValue.y + (fraction * 2 * (endValue.y - startValue.y))).toInt()
        } else {
            endValue.y
        }
        return point
    }
}