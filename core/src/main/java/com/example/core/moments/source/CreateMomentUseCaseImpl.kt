package com.example.core.moments.source

import com.example.core.moments.repository.MomentRepository
import com.example.core.moments.usecases.CreateMomentUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateMomentUseCaseImpl(
    private val repository: MomentRepository,
) : CreateMomentUseCase {

    override fun invoke(
        form: com.example.core.moments.models.CreateMomentForm,
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