/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.centrale.gowide.ui

import android.app.Activity
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun UserScreen(
    appViewModel: AppViewModel = viewModel(),
    modifier: Modifier,
    onSubmitButtonClicked: () -> Unit = {}
) {
    val gameUiState by appViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserLayout(
            onSubmitButtonClicked = onSubmitButtonClicked,
            appViewModel = appViewModel
        )


    }
}


@Composable
fun UserLayout(
    onSubmitButtonClicked: () -> Unit,
    appViewModel: AppViewModel
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val smallPadding = dimensionResource(R.dimen.padding_small)
    val tags = listOf("Italy", "Mountains", "Wine", "Wine", "Wine", "Wine")

    Column(
        verticalArrangement = Arrangement.spacedBy(smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(mediumPadding)
    ) {
        // Row avec le nom d'utilisateur et l'e-mail
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(smallPadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(smallPadding)
            ) {
                Text(text = stringResource(id = R.string.username_label))
                TextField(
                    value = "", // Valeur du nom d'utilisateur
                    onValueChange = { /* No-op */ },
                    modifier = Modifier,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        disabledContainerColor = colorScheme.surface,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(smallPadding)
            ) {
                Text(text = stringResource(id = R.string.email_label))
                TextField(
                    value = "", // Valeur de l'e-mail
                    onValueChange = { /* No-op */ },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        disabledContainerColor = colorScheme.surface,
                    ),
                    modifier = Modifier,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(smallPadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(smallPadding)
            ) {
                Text(text = stringResource(id = R.string.password_label))
                TextField(
                    value = "", // Valeur du nom d'utilisateur
                    onValueChange = { /* No-op */ },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        disabledContainerColor = colorScheme.surface,
                    ),
                    modifier = Modifier
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(smallPadding)
            ) {
                Text(text = stringResource(id = R.string.password_label))
                TextField(
                    value = "", // Valeur du mot de passe
                    onValueChange = { /* No-op */ },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        disabledContainerColor = colorScheme.surface,
                    ),
                    modifier = Modifier
                )
            }
        }
        Text(
            text = stringResource(id = R.string.interests_label)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            )
        ) {
            CardTags(
                modifier = Modifier,
                tags = appViewModel.tags
                )
        }
        Row {
            TextField(
                value = appViewModel.currentTag, // Valeur du tag actuel
                onValueChange = { appViewModel.updateCurrentTag(it) },
                placeholder = { Text(text = "Ajouter un tag") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                modifier = Modifier.weight(0.7f)
            )
            Button(
                onClick = { appViewModel.addElement() },
                modifier = Modifier.weight(0.3f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.navygreen)
                ),

            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }


        Button(
            onClick = onSubmitButtonClicked,
            modifier = Modifier
                .height(50.dp)
                .width(160.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.navygreen)
            )
        ) {
            Text(text = stringResource(id = R.string.log_out))
        }
    }
}

@Composable
fun CardTags(modifier: Modifier, tags : Set<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 10.dp,
            end = 10.dp,
            bottom = 10.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        content = {
            items(tags.size) { index ->
                Tag(
                    text = tags.elementAt(index),
                    modifier = Modifier
                )
            }
        }
    )
}

@Composable
fun Tag(text: String, modifier: Modifier) {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp)
            .height(25.dp)

    ) {
        Text(
            text = text,
            fontSize = 14.sp, // Taille de police plus petite
            modifier = Modifier.align(Alignment.Center))

    }
}


/*
 * Creates and shows an AlertDialog with final score.
 */


@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    GoWideTheme {
        UserScreen(
            modifier = Modifier.fillMaxHeight()
        )
    }
}
