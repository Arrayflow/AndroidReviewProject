package com.array.androidreviewproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.customview.CustomImageView
import com.array.androidreviewproject.customview.CustomViewActivity
import com.array.androidreviewproject.databinding.ActivityMainBinding
import com.array.androidreviewproject.ui.MainViewModel
import com.array.androidreviewproject.ui.MainViewModelFactory
import com.array.androidreviewproject.util.MainActivityObserver
import com.array.androidreviewproject.util.go2Activity


class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sp = getPreferences(Context.MODE_PRIVATE)
        val counter = sp.getInt("counter_reserved", 0)

        viewModel = ViewModelProvider(this, MainViewModelFactory(counter)).get(MainViewModel::class.java)

        /**
         * LiveData的使用
         */
        viewModel.counter.observe(this) { count ->
            binding.numTV.text = count.toString()
        }
        //上述Lambda表达式实际的形式
        viewModel.counter.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int) {
                binding.numTV.text = t.toString()
            }
        })
        viewModel.userName.observe(this) {
//            TODO("观察到userName变化后的操作")
        }

        binding.addBtn.setOnClickListener {
            viewModel.getUser("Tim")
        }

        viewModel.user.observe(this) { user ->
            binding.numTV.text = user.userName
        }

        binding.toRecyclerview.setOnClickListener {
            go2Activity<RecyclerViewActivity>(this)
        }

        binding.toDatabase.setOnClickListener {
            go2Activity<DataBaseActivity>(this)
        }

        binding.toWorkmgr.setOnClickListener {

        }

        binding.toCustomview.setOnClickListener {
            go2Activity<CustomViewActivity>(this)
        }



        lifecycle.addObserver(MainActivityObserver(lifecycle))
    }

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onPause() {
        super.onPause()
        sp.edit {
            viewModel.counter.value?.let {
                putInt("counter_reserved", it)
            }
        }
    }

}