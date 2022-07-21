package com.example.madplayground.ui.quotes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.domain.moments.models.Moment
import java.util.*

data class MomentState(
    override var id: String = UUID.randomUUID().toString(),
    override var description: String = "",
    override val author: String? = null
) : Moment.State

@Composable
fun rememberMomentState(
    initializer: MomentState.() -> Unit = {},
): MomentState = remember {
    MomentState().apply(initializer)
}
