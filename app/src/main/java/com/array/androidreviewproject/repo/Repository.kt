package com.array.androidreviewproject.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.array.androidreviewproject.bean.User

object Repository {
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User("Tim Cook $userId", 28)
        return liveData
    }
}