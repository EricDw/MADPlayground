package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import com.example.madplayground.domain.moments.usecases.RetrieveMomentUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class RetrieveMomentUseCaseImpl(
    private val repository: MomentRepository,
) : RetrieveMomentUseCase {
    override fun invoke(
        id: Moment.Id
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