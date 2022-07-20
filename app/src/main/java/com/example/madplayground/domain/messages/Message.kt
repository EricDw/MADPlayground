package com.example.madplayground.domain.messages

interface Message {

    fun interface Handler<M : Message> {

        operator fun invoke(message: M)

    }

}