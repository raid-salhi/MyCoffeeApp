package com.example.mycoffeeapp.repository

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mycoffeeapp.model.auth.AuthRequest
import com.example.mycoffeeapp.model.auth.AuthResult
import com.example.mycoffeeapp.model.auth.Token
import com.example.mycoffeeapp.network.AuthApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.math.log

class AuthRepositoryIml(  private val api: AuthApi, private val preferences: SharedPreferences) : AuthRepository{
    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun signUp(email: String, password: String): AuthResult<Unit> {
        var isLogged = false
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
                    if (response.code() == 200) {
                        Log.d("TAG", "signUp: Registration done")
                        GlobalScope.launch {
                            signIn(
                                email = email,
                                password=password
                            )
                            isLogged=true
                        }
                    }
                    else{
                        Log.d("TAG", "signUp: Registration failed ${response.code()}")
                    }
                }
            })
            if (isLogged) AuthResult.Authorized() else AuthResult.UnknownError()

        }catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                Log.d("TAG", "signUp: ${e.code()}")
                AuthResult.UnknownError()
            }
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<Unit> {
            var result = mutableStateOf<AuthResult<Unit>>(AuthResult.UnknownError())
            api.signIn(
                request = AuthRequest(
                    email = email,
                    password=password
                )
            ).enqueue(object :
                Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.d("TAG", "signIn: ${t.message}")
                    result.value=AuthResult.UnknownError()
                }
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.code() == 200) {
                        Log.d("TAG", "signIn: sing in done ${response.body()!!.value}")
                        preferences.edit()
                            .putString("jwt", response.body()!!.value)
                            .apply()
                       result.value=AuthResult.Authorized()
                    }
                    else{
                        Log.d("TAG", "signIn: sign in failed ${response.code()}")
                        result.value=AuthResult.Unauthorized()
                    }
                }
            })
        return result.value
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