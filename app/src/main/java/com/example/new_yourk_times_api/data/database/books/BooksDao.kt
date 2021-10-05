package com.example.new_yourk_times_api.data.database.books

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.new_yourk_times_api.data.domain.models.BooksDb

@Dao
interface BooksDao {
    @Query("SELECT * FROM books")
    fun getAll(): List<BooksDb>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<BooksDb>)

}