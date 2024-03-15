package com.noxis.hiltplayground.di

import android.content.Context
import androidx.room.Room
import com.noxis.hiltplayground.repository.dao.BlogDao
import com.noxis.hiltplayground.repository.dao.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDatabase(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}