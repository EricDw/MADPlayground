package com.example.madplayground.ui.moments.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.ui.screens.MomentFormScreen
import com.example.madplayground.ui.theme.models.LocalIconography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MomentFormTopAppBar(
    screen: MomentFormScreen,
    modifier: Modifier = Modifier,
) {

    val iconography = LocalIconography.current

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    with(screen) {
                        scope.launch {
                            if (scaffoldState.isConcealed) {
                                scaffoldState.reveal()
                            } else {
                                scaffoldState.conceal()
                            }
                        }
                    }

                },
                enabled = !screen.scaffoldState.isAnimationRunning,
            ) {

                val (vector, descriptionId) = if (screen.scaffoldState.isConcealed) {
                    iconography.menuIcon to R.string.description_open_menu
                } else {
                    iconography.closeIcon to R.string.description_close_menu
                }

                Icon(
                    imageVector = vector,
                    contentDescription = stringResource(id = descriptionId)
                )

            }

        },
        title = {
            Text(
                text = stringResource(id = R.string.title_compose_moment)
            )
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    )

}