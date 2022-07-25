package com.example.madplayground.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.madplayground.cache.moments.models.MomentDao
import com.example.madplayground.cache.moments.models.MomentEntity

@Database(
    entities = [MomentEntity::class],
    version = 1
)
abstract class MomentsRoomDataBase : RoomDatabase() {
    abstract fun momentDao(): MomentDao
}