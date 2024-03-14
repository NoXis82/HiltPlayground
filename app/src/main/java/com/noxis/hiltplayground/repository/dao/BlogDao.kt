package com.noxis.hiltplayground.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Query("DELETE FROM blogs")
    suspend fun clearAll()

    @Query("SELECT * FROM blogs")
    suspend fun getAll(): List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogEntity): Long

}