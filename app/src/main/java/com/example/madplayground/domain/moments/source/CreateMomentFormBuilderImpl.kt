package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm

internal class CreateMomentFormBuilderImpl : CreateMomentForm.Builder {

    override var description: String = ""

    fun build(): CreateMomentForm {
        return CreateMomentFormImpl(
            description = description
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
