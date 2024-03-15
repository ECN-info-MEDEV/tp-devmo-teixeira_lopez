package com.centrale.gowide.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.composable
import com.centrale.gowide.R
import com.centrale.gowide.ui.LoginScreen
import java.security.AccessController

enum class GoWideScreen (){
    Login,
    Profile
}

@Composable
fun GoWideApp (
    viewModel: AppViewModel = viewModel (),
    navController: NavHostController = rememberNavController()) {
    Scaffold() { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = GoWideScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = GoWideScreen.Login.name) {
                LoginScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onSubmitButtonClicked = {
                        navController.navigate(GoWideScreen.Profile.name)
                    }
                )
            }
            composable(route = GoWideScreen.Profile.name) {
                UserScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onSubmitButtonClicked = {
                        navController.navigate(GoWideScreen.Profile.name)
                    }
                )
            }


        }
    }

}

