package com.example.mycoffeeapp.network

import com.example.mycoffeeapp.model.auth.AuthRequest
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
    ): String

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}