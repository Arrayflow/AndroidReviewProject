package com.array.androidreviewproject.workmgr

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d(javaClass.simpleName, "定时运行!")
        return Result.success()
    }

}