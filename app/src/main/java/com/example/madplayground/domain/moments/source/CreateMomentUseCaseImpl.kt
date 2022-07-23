package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm
import com.example.madplayground.domain.moments.repository.MomentRepository
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateMomentUseCaseImpl(
    private val repository: MomentRepository,
) : CreateMomentUseCase {

    override fun invoke(
        form: CreateMomentForm,
    ): Flow<CreateMomentUseCase.Result> {

        return flow {

            emit(CreateMomentUseCase.Result.Running)

            val moment = try {

                MomentWriteModel(form = form)

            } catch (validationError: MomentWriteModel.ValidationError) {

                emit(
                    CreateMomentUseCase.Result.Error(
                        cause = validationError
                    )
                )

                return@flow

            }

            repository.addMoment(
                moment = moment
            )

            emit(CreateMomentUseCase.Result.Complete)

        }

    }

}