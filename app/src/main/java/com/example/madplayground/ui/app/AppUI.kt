package com.example.madplayground.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.ui.app.api.App
import com.example.madplayground.ui.theme.MADPlaygroundTheme

@Composable
fun App(
    state: App.State,
    eventHandler: App.Event.Handler,
    modifier: Modifier = Modifier,
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scaffoldState: ScaffoldState =
        rememberScaffoldState(drawerState = drawerState, snackbarHostState = snackbarHostState)

    MADPlaygroundTheme(
        darkTheme = state.isDarkTheme
    ) {

        Scaffold(scaffoldState = scaffoldState, modifier = modifier, content = content, topBar = {
            TopAppBar(title = {
                Text(text = state.title)
            }, actions = {
                IconButton(
                    onClick = {
                        eventHandler.handle(
                            App.Event.SettingsClick
                        )
                    }
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                }
            })
        })

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {

//    App(
//        modifier = Modifier.fillMaxSize(),
//    ) { scaffoldPadding ->
//
//        Box(
//            modifier = Modifier
//                .padding(scaffoldPadding)
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//
//            Text(
//                text = "Hello World",
//            )
//        }
//
//    }

}