package com.array.androidreviewproject.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.array.androidreviewproject.util.ActivityController
import java.lang.ref.WeakReference

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected val TAG = javaClass.simpleName

    protected lateinit var binding: VB

    private var activity: Activity? = null

    private var activityWR: WeakReference<Activity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = inflateViewBinding()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        activity = this
        activityWR = WeakReference(activity)
        activityWR?.let {
            ActivityController.pushTask(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity = null
        ActivityController.removeTask(activityWR)
    }

    abstract fun inflateViewBinding(): VB
}