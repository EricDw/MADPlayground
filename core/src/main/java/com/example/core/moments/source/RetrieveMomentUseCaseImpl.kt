package com.example.core.moments.source

import com.example.core.moments.repository.MomentRepository
import com.example.core.moments.usecases.RetrieveMomentUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrieveMomentUseCaseImpl(
    private val repository: MomentRepository,
) : RetrieveMomentUseCase {
    override fun invoke(
        id: com.example.core.moments.models.Moment.Id
    ): Flow<RetrieveMomentUseCase.Result> {
        return flow {

            emit(RetrieveMomentUseCase.Result.Running)

            repository.retrieveMomentById(
                id = id
            ).let {
                emit(RetrieveMomentUseCase.Result.Complete(it))
            }
        }
    }
}