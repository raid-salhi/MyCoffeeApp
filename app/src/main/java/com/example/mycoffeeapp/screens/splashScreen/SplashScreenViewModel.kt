package com.example.mycoffeeapp.screens.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycoffeeapp.model.auth.AuthResult
import com.example.mycoffeeapp.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel  @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()
    init {
        authenticate()
    }
    private fun authenticate(){
        viewModelScope.launch {
            val result = repository.authenticate()
            resultChannel.send(result)
        }
    }
}