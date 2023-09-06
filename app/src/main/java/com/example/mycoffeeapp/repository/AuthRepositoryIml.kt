package com.example.mycoffeeapp.repository

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.mycoffeeapp.model.auth.AuthRequest
import com.example.mycoffeeapp.model.auth.AuthResult
import com.example.mycoffeeapp.network.AuthApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.math.log

class AuthRepositoryIml(  private val api: AuthApi, private val preferences: SharedPreferences) : AuthRepository{
    override suspend fun signUp(email: String, password: String): AuthResult<Unit> {
        return try {
            api.signUp(
                request = AuthRequest(
                    email = email,
                    password=password
                )
            ).enqueue(object :
                Callback<AuthRequest> {
                override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                    Log.d("TAG", "signUp: ${t.message}")
                }
                override fun onResponse(call: Call<AuthRequest>, response: Response<AuthRequest>) {
                    if (response.code() == 201) {
                        Log.d("TAG", "signUp: Registration done")

                    }
                    else{
                        Log.d("TAG", "signUp: Registration failed ${response.code()}")
                    }
                }
            })
            signIn(
                email = email,
                password=password
            )

        }catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                Log.d("TAG", "signUp: ${e.code()}")
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            Log.d("TAG", "signUp: ${e.localizedMessage}")
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<Unit> {
        return try {
            val response=api.signIn(
                request = AuthRequest(
                    email = email,
                    password=password
                )
            )
            preferences.edit()
                .putString("jwt",response.value)
                .apply()
            AuthResult.Authorized()

        }catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = preferences.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

}