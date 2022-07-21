package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm
import com.example.madplayground.domain.moments.models.Moment
import java.util.*

class MomentWriteModel(
    form: CreateMomentForm,
) : Moment {

    data class ValidationError(
        val causes: List<Throwable>,
    ) : Throwable()

    init {

        val errors = mutableListOf<Throwable>()

        if (form.description.isBlank()) {

            errors + IllegalStateException(
                "Description can not be Empty"
            )

        }

        if (errors.isNotEmpty())
            throw ValidationError(
                causes = errors
            )

    }

    override val id: String = UUID.randomUUID().toString()

    override val description: String = form.description

    override val author: String? = null

}