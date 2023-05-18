package com.array.androidreviewproject.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(val bookName: String, val pages: Int, val author: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
