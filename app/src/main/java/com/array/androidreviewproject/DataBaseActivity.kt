package com.array.androidreviewproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.array.androidreviewproject.base.BaseActivity
import com.array.androidreviewproject.bean.UserInfo
import com.array.androidreviewproject.database.AppDatabase
import com.array.androidreviewproject.databinding.ActivityDataBaseBinding
import java.lang.Exception
import java.lang.StringBuilder
import kotlin.concurrent.thread

class DataBaseActivity : BaseActivity<ActivityDataBaseBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userInfoDao = AppDatabase.getDatabase(this).userInfoDao()



        binding.addUser.setOnClickListener {
            val userInfo1 = UserInfo("Tim", "Cook", 60)
            thread {
                userInfoDao.insertUserInfo(userInfo1)
            }
        }

        binding.deleteUser.setOnClickListener {
            val userInfo = UserInfo("Tim", "Cook", 60)

            thread {
                try {
                    userInfoDao.deleteUserInfoById(1L)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.showUser.setOnClickListener {
            val handler = Handler(Looper.getMainLooper())
            thread {
                val sb = StringBuilder()
                var userList: List<UserInfo>? = null
                try {
                    userList = userInfoDao.loadAllUserInfo()
                    userList.let {
                        for (user in it) {
                            sb.append(user)
                            sb.append("\n")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                handler.post {
                    binding.userInfo.text = sb.toString()
                }
            }
        }
    }

    override fun inflateViewBinding(): ActivityDataBaseBinding {
        return ActivityDataBaseBinding.inflate(layoutInflater)
    }
}