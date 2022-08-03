package com.example.madplayground.data.moments.fakes

import kotlinx.datetime.*

class FakeMoment : com.example.core.moments.models.Moment {

    override var id: com.example.core.moments.models.Moment.Id = com.example.core.moments.models.Moment.Id()

    override val createdDateTime: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.UTC)

    override var description: String = ""

    override var date: LocalDate? = null

    override var time: LocalTime? = null

    companion object {
        val MOMENT_1 = FakeMoment()
        val MOMENT_2 = FakeMoment()
    }

}