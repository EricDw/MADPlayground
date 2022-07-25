package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm

internal class CreateMomentFormBuilderImpl : CreateMomentForm.Builder {

    override var description: String = ""

    override var date: String? = null

    override var time: String? = null

    fun build(): CreateMomentForm {
        return CreateMomentFormImpl(
            description = description,
            date = date,
            time = time,
        )
    }

}

fun buildCreateMomentForm(
    initializer: CreateMomentForm.Builder.() -> Unit,
): CreateMomentForm {
    return CreateMomentFormBuilderImpl()
        .apply(initializer)
        .build()
}
