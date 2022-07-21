package com.example.madplayground.data.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MomentRepositoryImpl : MomentRepository {

    private val moments = mutableMapOf<Moment.Id, Moment>()

    private val currentMoment = MutableStateFlow<Moment?>(null)
    private val currentMomentReadFlow = currentMoment.asStateFlow()

    override suspend fun addMoment(
        moment: Moment
    ) {
        moments[moment.id] = moment
        currentMoment.update { moment }
    }

    override suspend fun getMoment(): StateFlow<Moment?> {
        return currentMomentReadFlow
    }
}