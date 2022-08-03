package com.example.core.moments.source

import kotlinx.datetime.*
import java.time.DateTimeException

class MomentWriteModel(
    form: com.example.core.moments.models.CreateMomentForm,
    clock: Clock = Clock.System,
) : com.example.core.moments.models.Moment {

    override val id: com.example.core.moments.models.Moment.Id = com.example.core.moments.models.Moment.Id()

    override lateinit var description: String

    override lateinit var createdDateTime: LocalDateTime

    override var date: LocalDate? = null

    override var time: LocalTime? = null

    init {

        val errors = mutableListOf<Throwable>()

        if (form.description.isBlank()) {

            errors + IllegalStateException(
                "Description can not be Empty"
            )

        } else {

            description = form.description

        }

        if (form.date?.isBlank() == true) {

            errors + IllegalStateException(
                "Time can not be blank"
            )

        } else {

            try {

                date = form.date?.let(LocalDate::parse)

            } catch (exception: DateTimeException) {
                errors + exception
            }

        }

        if (form.time?.isBlank() == true) {

            errors + IllegalStateException(
                "Time can not be blank"
            )

        } else {

            try {

                time = form.time?.let(LocalTime::parse)

            } catch (exception: DateTimeException) {
                errors + exception
            }

        }

        if (errors.isNotEmpty()) {
            throw ValidationError(
                causes = errors
            )
        }

        createdDateTime = clock.now().toLocalDateTime(TimeZone.UTC)

    }

    data class ValidationError(
        val causes: List<Throwable>,
    ) : Throwable()

}