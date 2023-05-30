package com.array.androidreviewproject.hilt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.databinding.ActivityHiltBinding
import com.array.androidreviewproject.hilt.bean.Student
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//在需要注入的Activity中添加注解，才能注入依赖
@AndroidEntryPoint
class HiltActivity : BaseActivity<ActivityHiltBinding>() {

    /**
     * Hilt注入
     */
    @Inject
    lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun inflateViewBinding(): ActivityHiltBinding {
        return ActivityHiltBinding.inflate(layoutInflater)
    }
}