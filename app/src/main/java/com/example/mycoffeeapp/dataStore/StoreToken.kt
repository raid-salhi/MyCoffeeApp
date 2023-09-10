package com.example.mycoffeeapp.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreToken (private val context: Context){
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Token")
        val token = stringPreferencesKey("token")

    }
    val getData: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[token] ?: ""
        }

    suspend fun saveData(name: String) {
        context.dataStore.edit { preferences ->
            preferences[token] = name
        }
    }
}