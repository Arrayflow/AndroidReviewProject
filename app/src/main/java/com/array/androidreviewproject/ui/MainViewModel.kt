package com.array.androidreviewproject.ui

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.array.androidreviewproject.bean.User
import com.array.androidreviewproject.repo.Repository

class MainViewModel(counterReserved: Int) : ViewModel() {
    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = counterReserved
    }

    fun add() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }



    /**
     * map()和switchMap()函数
     * map()：将一个LiveData对象经过包装并返回新的LiveData对象
     * switchMap()：使用场景固定，主要是在对在ViewModel之外获取的LiveData对象进行转换并观察
     */
    private val userLiveData = MutableLiveData<User>()
    //map()函数：
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        user.userName
    }
    //Activity中观察这个ViewModel
//    viewModel.userName.observe(this) { userName ->
//        TODO("观察到userName变化后的操作")
//    }

    //switchMap()函数：
    //定义一个可变的LiveData，接收getUser(userId)传进来的参数，并setValue给这个LiveData
    private val userIdLiveData = MutableLiveData<String>()
    //这里，首先是外部调用getUser(userId)方法，修改userIdLiveData的value，switchMap()方法会观察其变化，
    //随后运行转换函数，将从Repository中获得的LiveData转换为一个可观察的LiveData为user，之后就可以在Activity中观察
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }
    //此处带参即更改值，如果不带参则使等号前后相等即可，目的是调用setValue()方法，触发数据变化被观察
    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
//    binding.addBtn.setOnClickListener {
//        viewModel.getUser("Tim")
//    }
//
//    viewModel.user.observe(this) { user ->
//        TODO("$user 观察到变化之后的操作")
//    }

}