package com.example.madplayground.ui.moments.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.ui.moments.models.MomentFormUiState
import com.example.madplayground.ui.moments.source.rememberMomentFromScreenState
import com.example.madplayground.ui.screen.MomentFormScreen.Event
import com.example.madplayground.ui.theme.models.LocalIconography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun MomentFormScreen(
    modifier: Modifier = Modifier,
    state: MomentFormUiState = rememberMomentFromScreenState(),
    eventHandler: (Event) -> Unit = { /* no-op */ },
    showDialog: Boolean = false,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(
        initialValue = BackdropValue.Concealed
    ),
) {

    val iconography = LocalIconography.current

    val description by state.description.collectAsState()

    val date by state.date.collectAsState()

    val time by state.time.collectAsState()

    val submitting by state.submitting.collectAsState()

    val scope = rememberCoroutineScope()

    BackdropScaffold(
        appBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                if (scaffoldState.isConcealed) {
                                    scaffoldState.reveal()
                                } else {
                                    scaffoldState.conceal()
                                }
                            }

                        },
                        enabled = !scaffoldState.isAnimationRunning,
                    ) {

                        val (vector, descriptionId) = if (scaffoldState.isConcealed) {
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
        },
        backLayerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TextField(
                        value = date,
                        onValueChange = { newDate ->
                            eventHandler(
                                Event.DateChanged(
                                    newDate = newDate
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(1F)
                            .padding(
                                start = 16.dp,
                                top = 16.dp,
                                end = 8.dp,
                                bottom = 16.dp,
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = LocalIconography.current.dateIcon,
                                contentDescription = stringResource(id = R.string.description_date),
                                tint = MaterialTheme.colors.onPrimary
                            )
                        },
                        label = {

                            Text(
                                text = stringResource(id = R.string.label_date),
                                color = MaterialTheme.colors.onPrimary
                            )

                        },
                        singleLine = true,
                        readOnly = true,
                    )

                    TextField(
                        value = time,
                        onValueChange = { newDate ->
                            eventHandler(
                                Event.TimeChanged(
                                    newTime = newDate
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(1F)
                            .padding(
                                start = 8.dp,
                                top = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = LocalIconography.current.timeIcon,
                                contentDescription = stringResource(id = R.string.description_time),
                                tint = MaterialTheme.colors.onPrimary
                            )
                        },
                        label = {

                            Text(
                                text = stringResource(id = R.string.label_time),
                                color = MaterialTheme.colors.onPrimary
                            )

                        },
                        singleLine = true,
                        readOnly = true,
                    )

                }

            }
        },
        frontLayerContent = {
            LazyColumn(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newContent ->
                            eventHandler(
                                Event.ContentChanged(
                                    newContent = newContent
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = {

                            Text(
                                text = stringResource(id = R.string.label_description)
                            )

                        },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }

                item {

                    AnimatedContent(
                        targetState = submitting
                    ) {

                        if (submitting) {
                            Text(
                                text = stringResource(id = R.string.message_saving),
                                style = MaterialTheme.typography.h3
                            )
                        } else {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {

                                TextButton(
                                    onClick = {
                                        eventHandler(
                                            Event.CancelClicked
                                        )
                                    },
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(text = stringResource(id = R.string.label_cancel))
                                }

                                Button(
                                    onClick = {
                                        eventHandler(
                                            Event.SaveClicked
                                        )
                                    },
                                    modifier = Modifier.padding(16.dp),
                                    enabled = description.isNotBlank()
                                ) {
                                    Text(text = stringResource(id = R.string.label_save))
                                }

                            }

                        }

                    }

                }

                if (showDialog) {
                    item {
                        AlertDialog(
                            onDismissRequest = {
                                eventHandler(
                                    Event.DismissDialogRequested
                                )
                            },
                            title = {
                                Text(
                                    text = stringResource(
                                        id = R.string.title_discard_changes
                                    )
                                )
                            },
                            text = {
                                Text(
                                    text = stringResource(
                                        id = R.string.message_discard_changes
                                    )
                                )
                            },
                            buttons = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,

                                    ) {

                                    TextButton(
                                        onClick = {
                                            eventHandler(Event.DismissDialogRequested)
                                        }
                                    ) {
                                        Text(
                                            text = stringResource(
                                                id = R.string.label_no
                                            )
                                        )
                                    }

                                    Button(
                                        onClick = {
                                            eventHandler(Event.DiscardChangesClicked)
                                        }
                                    ) {
                                        Text(
                                            text = stringResource(
                                                id = R.string.label_discard
                                            )
                                        )
                                    }

                                }

                            }
                        )
                    }
                }

            }

        },
        modifier = modifier,
        scaffoldState = scaffoldState,
        stickyFrontLayer = true,
        gesturesEnabled = scaffoldState.isRevealed,
    )

    BackHandler {
        eventHandler(
            Event.BackClicked
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun MomentFromScreenPreview() {
    MomentFormScreen(
        modifier = Modifier.fillMaxSize(),
        showDialog = true
    )
}