package com.example.madplayground.data.moments.fakes

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FakeMoment : Moment {

    override var id: Moment.Id = Moment.Id()

    override var description: String = ""

    override var date: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.UTC)

    companion object {
        val MOMENT_1 = FakeMoment()
        val MOMENT_2 = FakeMoment()
    }

}