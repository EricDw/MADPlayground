package com.example.madplayground.domain.moments.usecases

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow

fun interface RetrieveMomentUseCase {

    sealed interface Result {

        object Running: Result

        data class Complete(
            val moment: Moment?
        ): Result

        object Error: Result
    }

    operator fun invoke(): Flow<Result>

}