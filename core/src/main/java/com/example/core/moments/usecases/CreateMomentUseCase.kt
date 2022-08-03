package com.example.core.moments.usecases

import kotlinx.coroutines.flow.Flow

fun interface CreateMomentUseCase {

    sealed interface Result {
        object Running: Result
        object Complete: Result
        class Error(
            val cause: Throwable
        ): Result
    }

    operator fun invoke(
        form: com.example.core.moments.models.CreateMomentForm
    ): Flow<Result>

}