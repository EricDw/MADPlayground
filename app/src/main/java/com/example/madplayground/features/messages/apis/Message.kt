package com.example.madplayground.features.messages.apis

interface Message {

    fun interface Handler<M : Message> {

        operator fun invoke(message: M)

    }

}