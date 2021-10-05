package com.example.new_yourk_times_api.data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewsDb(

    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "snippet")
    val snippet: String,

    @ColumnInfo(name = "section")
    val section: String,

    @ColumnInfo(name = "pubDate")
    val pubDate: String
)