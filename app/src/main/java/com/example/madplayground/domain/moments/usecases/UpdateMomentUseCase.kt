package com.example.madplayground.domain.moments.usecases

import kotlinx.coroutines.flow.Flow

fun interface UpdateMomentUseCase {

    sealed interface Result {
        object Running: Result
        object Complete: Result
        object Error: Result
    }

    operator fun invoke(): Flow<Result>

}