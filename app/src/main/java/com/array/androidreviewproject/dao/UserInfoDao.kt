package com.array.androidreviewproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.array.androidreviewproject.bean.UserInfo

@Dao
interface UserInfoDao {

    @Insert
    fun insertUserInfo(userInfo: UserInfo): Long

    @Update
    fun updateUserInfo(newUserInfo: UserInfo)

    @Query("select * from UserInfo")
    fun loadAllUserInfo(): List<UserInfo>

    @Delete
    fun deleteUserInfo(userInfo: UserInfo)

    @Query("delete from UserInfo where lastName = :lastName")
    fun deleteUserInfoByLastName(lastName: String)

    @Query("select * from UserInfo where age > :age")
    fun loadUserInfoOlderThan(age: Int): List<UserInfo>

    @Query("delete from UserInfo where id = :id")
    fun deleteUserInfoById(id: Long): Int
}