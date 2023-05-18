package com.array.androidreviewproject.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

//实体类添加注解
@Entity
data class UserInfo(val firstName: String, val lastName: String, val age: Int) {

    //添加主键id选项
    @PrimaryKey( autoGenerate = true)
    var id: Long = 0
}