package com.example.core.moments.usecases

import kotlinx.coroutines.flow.Flow

fun interface DeleteMomentUseCase {

    sealed interface Result {
        object Running: Result
        object Complete: Result
        object Error: Result
    }

    operator fun invoke(): Flow<Result>

}