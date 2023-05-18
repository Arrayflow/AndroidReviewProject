package com.array.androidreviewproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.array.androidreviewproject.bean.Book
import com.array.androidreviewproject.dao.UserInfoDao
import com.array.androidreviewproject.bean.UserInfo
import com.array.androidreviewproject.dao.BookDao

@Database(version = 3, entities = [UserInfo::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

    abstract fun bookDao(): BookDao

    companion object {
        private var instance: AppDatabase? = null

        /**
         * 更新数据库
         */
        //添加一张表
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //由于新添加一张表，则需要写sql语句来新建一张表
                database.execSQL(
                    "create table Book " +
                            "(id integer primary key autoincrement not null, " +
                            "bookName text not null, " +
                            "pages integer not null)"
                )
            }

        }
        //表中增加一列/行
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            //数据库升级时，则需要在这里添加
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "user_database")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    instance = this
                }
        }
    }
}