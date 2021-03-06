package com.example.cache.moments.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "moment_table"
)
data class MomentEntity @JvmOverloads constructor(
    @PrimaryKey
    val uid: String,
    val created_date_time: String,
    val description: String,
    val date: String? = null,
    val time: String? = null,
)
