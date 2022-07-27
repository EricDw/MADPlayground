package com.example.madplayground.ui.container.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.madplayground.ui.container.models.ContentContainer

@Composable
fun ContentContainer.ContentContainerFloatingActionButton(
    modifier: Modifier = Modifier,
) {

//    AnimatedVisibility(
//        visible = showBottomFAB,
//        modifier = modifier,
//        enter = expandIn(expandFrom = Alignment.Center),
//        exit = shrinkOut(shrinkTowards = Alignment.Center),
//    ) {

    if (showBottomFAB)
        bottomFAB()
//
//}

}