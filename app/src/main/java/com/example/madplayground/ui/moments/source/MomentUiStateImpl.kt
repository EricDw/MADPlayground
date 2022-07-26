package com.example.madplayground.ui.moments.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.ui.moments.models.MomentUiState
import java.util.*

data class MomentUiStateImpl(
    override var id: String = "",
    override var description: String = "",
    override var date: String? = null,
    override var time: String? = null,
) : MomentUiState

@Composable
fun rememberMomentUiState(
    initializer: MomentUiStateImpl.() -> Unit = {},
): MomentUiState = remember {
    MomentUiStateImpl().apply(initializer)
}
