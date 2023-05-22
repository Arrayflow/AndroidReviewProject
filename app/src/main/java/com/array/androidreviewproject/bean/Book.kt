package com.array.androidreviewproject.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var bookName: String, var pages: Int, var author: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
