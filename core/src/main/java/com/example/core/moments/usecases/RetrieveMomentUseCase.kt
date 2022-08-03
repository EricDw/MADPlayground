package com.example.core.moments.usecases

import kotlinx.coroutines.flow.Flow

fun interface RetrieveMomentUseCase {

    sealed interface Result {

        object Running: Result

        data class Complete(
            val moment: com.example.core.moments.models.Moment?
        ): Result

        object Error: Result
    }

    operator fun invoke(
        id: com.example.core.moments.models.Moment.Id
    ): Flow<Result>

}