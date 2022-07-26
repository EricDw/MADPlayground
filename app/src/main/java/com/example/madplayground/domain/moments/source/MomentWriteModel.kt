package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm
import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.*
import java.time.DateTimeException

class MomentWriteModel(
    form: CreateMomentForm,
    clock: Clock = Clock.System,
) : Moment {

    override val id: Moment.Id = Moment.Id()

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