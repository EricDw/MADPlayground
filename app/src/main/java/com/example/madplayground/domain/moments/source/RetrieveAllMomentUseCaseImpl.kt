package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.repository.MomentRepository
import com.example.madplayground.domain.moments.usecases.RetrieveAllMomentUseCase
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
                    repository.getAllMoments()
                )
            )

        }

    }

}