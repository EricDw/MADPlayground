package com.example.madplayground.data.moments.models

import kotlinx.coroutines.flow.Flow

interface LocalMomentDataSource {

    suspend fun addMomentData(
        moment: MomentData,
    )

    suspend fun retrieveMomentDataById(
        id: String,
    ): MomentData?

    suspend fun retrieveAllMomentData(): List<MomentData>

    suspend fun retrieveAllMomentDataFlow(): Flow<List<MomentData>>

}