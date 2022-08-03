package com.example.core.moments.usecases

import kotlinx.coroutines.flow.Flow

fun interface RetrieveAllMomentUseCase {

    sealed interface Result {

        object Running: Result

        data class Complete(
            val moments: Flow<List<com.example.core.moments.models.Moment>>
        ): Result

        object Error: Result
    }

    operator fun invoke(): Flow<Result>

}