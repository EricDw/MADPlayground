package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm
import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.LocalDateTime
import java.util.*

class MomentWriteModel(
    form: CreateMomentForm,
) : Moment {

    override val id: Moment.Id = Moment.Id(UUID.randomUUID().toString())

    override lateinit var description: String

    override lateinit var date: LocalDateTime

    init {

        val errors = mutableListOf<Throwable>()

        if (form.description.isBlank()) {

            errors + IllegalStateException(
                "Description can not be Empty"
            )

        } else {

            description = form.description

        }

        if (form.date.isBlank()) {

            errors + IllegalStateException(
                "Date can not be Empty"
            )

        } else {

            date = LocalDateTime.parse(form.date)

        }

        if (errors.isNotEmpty())
            throw ValidationError(
                causes = errors
            )

    }

    data class ValidationError(
        val causes: List<Throwable>,
    ) : Throwable()

}