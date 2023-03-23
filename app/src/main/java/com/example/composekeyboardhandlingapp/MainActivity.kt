package com.example.composekeyboardhandlingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //use scaffold please
            //add top and bottom app bars - insert links

            //TopBar()

            Scaffold() {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UpperPanel()

                    LowerPanel()
                }

            }

            // BottomAppBar()

        }
    }
}

@Composable
fun UpperPanel() {
    Column {

        Image(
            painter = painterResource(id = R.drawable.ic_6_ft_apart),
            contentDescription = ""
        )

        Divider(
            color = Color.Green,
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 10.dp
                )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LowerPanel() {

    //This API is experimental and is likely to change in the future.
    val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
    //show keyboard
    keyboardController?.show()
    //hide keyboard
    keyboardController?.hide()

    var textState by remember {
        mutableStateOf("using keyboardController")
    }


    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(16.dp)
    ) {

        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(text = "Start typing here") },
            placeholder = { Text("Type something here") },
            modifier = Modifier.background(color = Color.Yellow),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            //hide keyboard once user taps DONE button
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            )
        )
    }

    Divider(color = Color.Green, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

    //  Alternative keyboard options:-
    //  keyboardOptions = KeyboardOptions.Default.copy(
    //  keyboardType = KeyboardType.Ascii

    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(16.dp)
    ) {

        val focusManager = LocalFocusManager.current
        var text2 by remember {
            mutableStateOf("using focusManager")
        }

        val isVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
        LaunchedEffect(key1 = isVisible) {
            if (isVisible) {
                //hide FAB
            } else {
                //show FAB
            }
        }


        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text(text = "Start typing here") },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),


            )

    }
}