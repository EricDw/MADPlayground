package com.example.madplayground.data.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MomentRepositoryImpl : MomentRepository {

    private val moments = mutableMapOf<Moment.Id, Moment>()

    private val _momentsStateFlow =
        MutableStateFlow(
            moments.values.toList()
        )

    private val momentsStateFlow =
        _momentsStateFlow.asStateFlow()

    private val currentMoment = MutableStateFlow<Moment?>(null)

    private val currentMomentReadFlow = currentMoment.asStateFlow()

    override suspend fun addMoment(
        moment: Moment
    ) {
        moments[moment.id] = moment
        currentMoment.update { moment }
        _momentsStateFlow.update { moments.values.toList() }
    }

    override suspend fun getMomentById(
        id: Moment.Id
    ): Moment? {
        return moments[id]
    }

    override suspend fun getAllMoments(): List<Moment> {
        return moments.values.toList()
    }

    override suspend fun getAllMomentsFlow(): StateFlow<List<Moment>> {
        return momentsStateFlow
    }

}