package com.example.mycoffeeapp.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycoffeeapp.model.auth.AuthResult
import com.example.mycoffeeapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) :ViewModel() {
    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()
    fun signUp(email:String,password:String){
        viewModelScope.launch {

            val result = repository.signUp(
                email = email,
                password = password
            )
            resultChannel.send(result)
        }
    }
}