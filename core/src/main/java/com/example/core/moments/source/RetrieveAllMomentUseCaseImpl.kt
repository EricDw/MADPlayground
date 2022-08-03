package com.example.core.moments.source

import com.example.core.moments.repository.MomentRepository
import com.example.core.moments.usecases.RetrieveAllMomentUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrieveAllMomentUseCaseImpl(
    private val repository: MomentRepository,
) : RetrieveAllMomentUseCase {

    override fun invoke(): Flow<RetrieveAllMomentUseCase.Result> {

        return flow {

            emit(
                RetrieveAllMomentUseCase.Result.Running
            )

            emit(
                RetrieveAllMomentUseCase.Result.Complete(
                    repository.retrieveAllMomentsFlow()
                )
            )

        }

    }

}