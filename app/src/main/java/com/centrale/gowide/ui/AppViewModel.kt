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
import kotlinx.coroutines.flow.update


class AppViewModel: ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    var usernameGuess by mutableStateOf("")
        private set

    var currentTag by mutableStateOf("")
        private set

    var passwordGuess by mutableStateOf("")
        private set

    var mail by mutableStateOf("")
        private set

    var missedPass by mutableStateOf(false)
        private set
    var callClicked by mutableStateOf(false)
        private set

    var tags: MutableSet<String> = mutableSetOf()
        private set

    fun updateUsername(newUsername: String){
        usernameGuess = newUsername
    }

    fun updateCurrentTag(newTag: String){
        currentTag = newTag
    }
    fun updatePassword(guessedPass: String){
         passwordGuess = guessedPass
    }
    fun updateMissedPass(retry: Boolean){
        missedPass = retry
    }

    fun updateMail(newMail: String){
        mail = newMail
    }

    fun updateCallClicked (state: Boolean){
        callClicked = state
    }

    fun addElement() : Boolean {
        if(!tags.contains(currentTag)) {
            tags.add(currentTag)
            currentTag = ""
            return true
        }
        return false
    }

    fun verifyCredentials(
        onSuccess: () -> Unit
    ) {
        // Vérifie si le Pair(username, password) est présent dans la liste

        val isValid = users.any { it[0] == usernameGuess && it[1] == passwordGuess }

        if (isValid) {
            _uiState.update { currentState ->
                currentState.copy(username = usernameGuess, password = passwordGuess)
            }
            val user = users.first { it[0] == usernameGuess && it[1] == passwordGuess }
            val mail = user[3].toString()

            val newTags = user[4] as? List<String>
            if (newTags != null) {
                for (tag in newTags) {
                    tags.add(tag.toString())
                }
            }
            updateMail(mail)
            onSuccess()
        } else {
            updateMissedPass(true)
        }
    }
    fun deleteCredentials(
        onSuccess: () -> Unit
    ) {
       _uiState.update { currentState ->
                currentState.copy(username = "", password = "")
       }
        updateUsername("")
        updatePassword("")
        onSuccess()
    }
}