package com.example.new_yourk_times_api.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.new_yourk_times_api.data.domain.models.NewsDb

@Database(
    entities = [
        NewsDb::class
    ],
    version = 1
)

abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao
}