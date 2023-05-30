package com.array.androidreviewproject.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 所有使用 Hilt 的应用都必须包含一个带有 @HiltAndroidApp 注解的 Application 类。
 * 生成的这一 Hilt 组件会附加到 Application 对象的生命周期，并为其提供依赖项。
 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}