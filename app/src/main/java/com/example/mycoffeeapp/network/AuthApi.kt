package com.example.mycoffeeapp.network

import com.example.mycoffeeapp.model.auth.AuthRequest
import com.example.mycoffeeapp.model.auth.Token
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {
    @POST("signup")
    suspend fun signUp(
        @Body request: AuthRequest
    )

    @POST("signin")
    suspend fun signIn(
        @Body request: AuthRequest
    ): Token

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}