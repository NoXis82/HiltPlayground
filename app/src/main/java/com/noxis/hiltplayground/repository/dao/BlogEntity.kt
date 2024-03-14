package com.noxis.hiltplayground.repository.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("body")
    val body: String,

    @ColumnInfo("image")
    val image: String,

    @ColumnInfo("category")
    val category: String
)
