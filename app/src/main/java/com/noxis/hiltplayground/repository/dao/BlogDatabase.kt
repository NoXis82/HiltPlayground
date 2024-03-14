package com.noxis.hiltplayground.repository.dao

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [BlogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BlogDatabase: RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        const val DATABASE_NAME: String = "blog_db"
    }
}