package com.example.mycoffeeapp.repository

import com.example.mycoffeeapp.model.auth.AuthResult

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthResult<Unit>
    suspend fun signIn(email: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}