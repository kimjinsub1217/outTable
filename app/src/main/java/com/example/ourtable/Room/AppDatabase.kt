package com.example.ourtable.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ourtable.dao.ReviewDao
import com.example.ourtable.model.Review

@Database(entities = [Review::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
}