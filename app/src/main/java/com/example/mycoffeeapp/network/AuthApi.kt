package com.example.mycoffeeapp.network

import com.example.mycoffeeapp.model.auth.AuthRequest
import com.example.mycoffeeapp.model.auth.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface AuthApi {
    @POST("signup")
    @Headers("Content-Type: application/json")
    fun signUp(
        @Body request: AuthRequest
    ): Call<AuthRequest>

    @POST("signin")
    @Headers("Content-Type: application/json")
    suspend fun signIn(
        @Body request: AuthRequest
    ): Token

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}