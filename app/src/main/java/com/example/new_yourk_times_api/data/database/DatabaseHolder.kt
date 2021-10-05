package com.example.new_yourk_times_api.data.database

import androidx.room.Room
import com.example.new_yourk_times_api.application.App

object DatabaseHolder {

    private const val DATABASE_NAME = "news_database"

    val newsDatabase: NewsDatabase by lazy { createDatabase() }

    private fun createDatabase(): NewsDatabase = Room.databaseBuilder(
        App.applicationContext(),
        NewsDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}
