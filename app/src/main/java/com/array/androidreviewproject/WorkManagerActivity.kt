package com.array.androidreviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.databinding.ActivityWorkManagerBinding
import com.array.androidreviewproject.util.ActivityController
import com.array.androidreviewproject.workmgr.SimpleWorker
import java.util.concurrent.TimeUnit

class WorkManagerActivity : BaseActivity<ActivityWorkManagerBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.doWork.setOnClickListener {
            val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }

    override fun inflateViewBinding(): ActivityWorkManagerBinding {
        return ActivityWorkManagerBinding.inflate(layoutInflater)
    }
}