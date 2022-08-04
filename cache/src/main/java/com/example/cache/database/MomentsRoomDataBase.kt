package com.example.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.moments.models.MomentDao
import com.example.cache.moments.models.MomentEntity

@Database(
    entities = [MomentEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class MomentsRoomDataBase : RoomDatabase() {
    abstract fun momentDao(): MomentDao
}