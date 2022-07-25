package com.example.madplayground.data.moments.fakes

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.*

class FakeMoment : Moment {

    override var id: Moment.Id = Moment.Id()

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