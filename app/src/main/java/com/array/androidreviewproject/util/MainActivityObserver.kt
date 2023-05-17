package com.array.androidreviewproject.util

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainActivityObserver(val lifecycle: Lifecycle) : LifecycleObserver {
    //添加需要探测生命周期状态的注解，即可回调该函数
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MainA", "START!")
        //由于将lifecycle作为参数传到此类中，即可在任意地方调用lifecycle.currentState获取Activity的状态
        lifecycle.currentState
    }
}