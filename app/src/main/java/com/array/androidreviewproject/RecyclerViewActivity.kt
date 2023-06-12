package com.array.androidreviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.bean.User
import com.array.androidreviewproject.databinding.ActivityRecyclerViewBinding
import com.array.androidreviewproject.widget.MyAdapter
import java.util.ArrayList

class RecyclerViewActivity : BaseActivity<ActivityRecyclerViewBinding>() {

    private lateinit var adapter: MyAdapter

    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userList = ArrayList()
        repeat(10) {
            userList.run {
                add(User("AA", 18))
                add(User("BB", 19))
                add(User("CC", 20))
                add(User("DD", 21))
                add(User("EE", 22))
            }
        }

        adapter = MyAdapter(userList)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter
    }

    override fun inflateViewBinding(): ActivityRecyclerViewBinding {
        return ActivityRecyclerViewBinding.inflate(layoutInflater)
    }


}