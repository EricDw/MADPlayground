package com.example.madplayground.domain.moments.usecases

import com.example.madplayground.domain.moments.models.CreateMomentForm
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
        form: CreateMomentForm
    ): Flow<Result>

}