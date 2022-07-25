package com.example.madplayground.cache.moments.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MomentDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
    )
    suspend fun insertEntity(entity: MomentEntity)

    @Query("SELECT * FROM moment_table WHERE uid = :uid")
    suspend fun retrieveById(uid: String): MomentEntity?

    @Query("SELECT * FROM moment_table")
    suspend fun retrieveAll(): List<MomentEntity>

    @Query("SELECT * FROM moment_table")
    fun retrieveAllFlow(): Flow<List<MomentEntity>>

}