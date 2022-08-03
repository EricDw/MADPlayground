package com.example.core.moments.source

internal class CreateMomentFormBuilderImpl : com.example.core.moments.models.CreateMomentForm.Builder {

    override var description: String = ""

    override var date: String? = null

    override var time: String? = null

    fun build(): com.example.core.moments.models.CreateMomentForm {
        return CreateMomentFormImpl(
            description = description,
            date = date,
            time = time,
        )
    }

}

fun buildCreateMomentForm(
    initializer: com.example.core.moments.models.CreateMomentForm.Builder.() -> Unit,
): com.example.core.moments.models.CreateMomentForm {
    return CreateMomentFormBuilderImpl()
        .apply(initializer)
        .build()
}
