package com.example.todolist.common.app_settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStore(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "theme_preferences")

    private val themeKey = stringPreferencesKey("theme_key")
    val themeFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[themeKey] ?: "default"
        }
    suspend fun saveTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = theme
        }
    }

    private val colorSystemKey = stringPreferencesKey("color_system_key")
    val colorSystemFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[colorSystemKey] ?: "default"
        }
    suspend fun saveColorSystem(colorSystem: String) {
        context.dataStore.edit { preferences ->
            preferences[colorSystemKey] = colorSystem
        }
    }
}