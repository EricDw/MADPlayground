package com.example.madplayground.messages.api

interface Message {

    fun interface Handler<M : Message> {

        object EMPTY: Handler<Message> {
            override fun invoke(message: Message) {
                /* no-op */
            }
        }

        operator fun invoke(message: M)

    }

}