package com.example.madplayground.ui.moments.source

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.ui.moments.models.MomentFormScreen.Event
import com.example.madplayground.ui.moments.models.MomentFormUiState
import com.example.madplayground.ui.theme.models.LocalIconography

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MomentFormScreen(
    modifier: Modifier = Modifier,
    state: MomentFormUiState = rememberMomentFromScreenState(),
    eventHandler: (Event) -> Unit = { /* no-op */ },
    showDialog: Boolean = false,
) {

    val iconography = LocalIconography.current

    val description by state.description.collectAsState()

    val date by state.date.collectAsState()

    val time by state.time.collectAsState()

    val submitting by state.submitting.collectAsState()

    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier,
    ) {
        item {
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
                            imageVector = iconography.dateIcon,
                            contentDescription = stringResource(id = R.string.description_date),
                        )
                    },
                    label = {

                        Text(
                            text = stringResource(id = R.string.label_date),
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
                            imageVector = iconography.timeIcon,
                            contentDescription = stringResource(id = R.string.description_time),
                        )
                    },
                    label = {

                        Text(
                            text = stringResource(id = R.string.label_time),
                        )

                    },
                    singleLine = true,
                    readOnly = true,
                )

            }
        }

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
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        Button(
                            onClick = {
                                eventHandler(
                                    Event.SaveClicked
                                )
                            },
                            modifier = Modifier.padding(16.dp),
                            enabled = description.isNotBlank(),
                            shape = MaterialTheme.shapes.small,
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
                    dismissButton = {
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

                    },
                    confirmButton =  {
                        Button(
                            onClick = {
                                eventHandler(Event.DiscardChangesClicked)
                            },
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.label_discard
                                )
                            )
                        }

                    }
                )
            }
        }

    }

    BackHandler {
        eventHandler(
            Event.BackClicked
        )
    }

}

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