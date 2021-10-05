package com.example.new_yourk_times_api.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.new_yourk_times_api.data.database.books.BooksDao
import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.data.domain.models.NewsDb

@Database(
    entities = [
        NewsDb::class,
        BooksDb::class
    ],
    version = 2
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun booksDao(): BooksDao
}