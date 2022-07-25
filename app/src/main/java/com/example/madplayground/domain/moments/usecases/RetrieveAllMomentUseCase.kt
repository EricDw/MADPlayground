package com.example.madplayground.domain.moments.usecases

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

fun interface RetrieveAllMomentUseCase {

    sealed interface Result {

        object Running: Result

        data class Complete(
            val moments: Flow<List<Moment>>
        ): Result

        object Error: Result
    }

    operator fun invoke(): Flow<Result>

}