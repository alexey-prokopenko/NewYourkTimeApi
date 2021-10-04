package com.example.new_yourk_times_api.data.database

import androidx.room.*
import com.example.new_yourk_times_api.data.domain.models.NewsDb



@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsDb>

   @Query ("SELECT * FROM news WHERE snippet LIKE :snippet")
   fun findBySnippet(snippet: String): NewsDb

    @Query("SELECT * FROM news WHERE section LIKE :section")
    fun findBySection (section: String) : NewsDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stories: List<NewsDb>)

    @Delete
    fun delete(user: NewsDb)
}