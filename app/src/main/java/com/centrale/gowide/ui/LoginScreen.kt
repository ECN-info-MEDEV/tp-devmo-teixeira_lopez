
package com.centrale.gowide.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.centrale.gowide.R
import com.centrale.gowide.ui.theme.GoWideTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun LoginScreen(
    appViewModel: AppViewModel = viewModel(),
    modifier: Modifier,
    onSubmitButtonClicked: () -> Unit = {}
) {
    val gameUiState by appViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val largePadding = dimensionResource(R.dimen.padding_large)
    val image = painterResource(R.drawable.logo_go)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(largePadding)
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .size(200.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = image,
                    contentDescription = null
                )
            }
            LoginLayout(
                onUserGuessChanged = { appViewModel.updateUsername(it) },
                onPasswordChanged = { appViewModel.updatePassword(it) },
                userGuess = appViewModel.username,
                passwordGuess = appViewModel.password,
                onKeyboardDone = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(mediumPadding)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding),
                verticalArrangement = Arrangement.spacedBy(mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.navygreen)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { appViewModel.verifyCredentials(onSubmitButtonClicked) }
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        fontSize = 24.sp
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(mediumPadding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(mediumPadding)
        ) {
            TextButton(
                onClick = onSubmitButtonClicked,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.help),
                    fontSize = 20.sp,
                    color = colorResource(R.color.navygreen)
                )
            }
            TextButton(
                onClick = onSubmitButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.register),
                    fontSize = 20.sp,
                    color = colorResource(R.color.navygreen)
                )
            }
        }
    }
}


@Composable
fun LoginLayout(
    onUserGuessChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    userGuess: String,
    passwordGuess: String,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    Box(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(mediumPadding)
        ) {
            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                onValueChange = onUserGuessChanged,
                label = { Text(stringResource(R.string.username)) },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                )
            )
            OutlinedTextField(
                value = passwordGuess,
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                visualTransformation = PasswordVisualTransformation(), // Hide the password
                onValueChange = onPasswordChanged,
                label = { Text(stringResource(R.string.password)) },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                )
            )
        }
    }
}

/*
 * Creates and shows an AlertDialog with final score.
 */
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GoWideTheme {
        LoginScreen(
        modifier=Modifier.fillMaxHeight()
        )
    }
}


