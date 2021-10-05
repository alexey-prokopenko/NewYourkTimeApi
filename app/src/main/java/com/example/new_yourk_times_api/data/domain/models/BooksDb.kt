package com.example.new_yourk_times_api.data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class BooksDb(

    @PrimaryKey
    @ColumnInfo(name = "urlAmazon")
    val urlAmazon: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String

)