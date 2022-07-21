package com.example.madplayground.ui.quotes.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.quotes.models.MomentUiState
import java.util.*

data class MomentUiStateImpl(
    override var id: String = UUID.randomUUID().toString(),
    override var description: String = "",
    override val author: String? = null
) : MomentUiState

@Composable
fun rememberMomentUiState(
    initializer: MomentUiStateImpl.() -> Unit = {},
): MomentUiState = remember {
    MomentUiStateImpl().apply(initializer)
}
