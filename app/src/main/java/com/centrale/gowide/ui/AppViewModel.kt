package com.centrale.gowide.ui
import android.app.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.centrale.gowide.data.users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class AppViewModel: ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var missedPass by mutableStateOf(false)
        private set
    var callClicked by mutableStateOf(false)
        private set
    fun updateUsername(guessedUser: String){
        username = guessedUser
    }
    fun updatePassword(guessedPass: String){
        password = guessedPass
    }
    fun updateMissedPass(retry: Boolean){
        missedPass = retry
    }

    fun updateCallClicked (state: Boolean){
        callClicked = state
    }

    fun verifyCredentials(
        onSuccess: () -> Unit
    ) {
        // Vérifie si le Pair(username, password) est présent dans la liste
        val isValid = users.any { it.first == username && it.second == password }
        if (isValid) {
            onSuccess() // Exécute la fonction onSuccess si les identifiants sont valides
        } else {
            updateMissedPass(true)
        }
    }
}