package com.example.madplayground.features.quotes.ui.screens.form

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen.Event
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen.State

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun QuoteFormScreen(
    modifier: Modifier = Modifier,
    state: State = rememberQuoteFromScreenState(),
    eventHandler: Message.Handler<Event> = Message.Handler { /* no-op */ },
) {

    val content by state.content.collectAsState()

    val author by state.author.collectAsState()

    val submitting by state.submitting.collectAsState()

    BackdropScaffold(
        appBar = {
        },
        backLayerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                TextField(
                    value = author,
                    onValueChange = { newAuthor ->
                        eventHandler(
                            Event.AuthorChanged(
                                newAuthor = newAuthor
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = {

                        Text(
                            text = stringResource(id = R.string.label_author),
                            color = MaterialTheme.colors.onPrimary
                        )

                    },
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        },
        frontLayerContent = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = content,
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
                            text = stringResource(id = R.string.label_content)
                        )

                    },
                )

                Spacer(modifier = Modifier.height(8.dp))

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
                                enabled = content.isNotBlank()
                            ) {
                                Text(text = stringResource(id = R.string.label_save))
                            }

                        }

                    }

                }

            }

        },
        stickyFrontLayer = true,
    )

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun QuoteFromScreenPreview() {
    QuoteFormScreen(
        modifier = Modifier.fillMaxSize()
    )
}