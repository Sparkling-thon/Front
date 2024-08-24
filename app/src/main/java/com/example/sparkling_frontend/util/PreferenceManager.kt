package com.example.sparkling_frontend.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val PREFS_NAME = "sparkling_prefs"
    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_AUTH_TYPE = "auth_type"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveAuthToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_AUTH_TOKEN, token)
        editor.apply()
    }

    fun getAuthToken(context: Context): String? {
        return getPreferences(context).getString(KEY_AUTH_TOKEN, null)
    }

    fun saveAuthType(context: Context, authType: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_AUTH_TYPE, authType)
        editor.apply()
    }

    fun getAuthType(context: Context): String? {
        return getPreferences(context).getString(KEY_AUTH_TYPE, null) // 값이 없으면 null 반환
    }

    fun clearAuthData(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(KEY_AUTH_TOKEN)
        editor.remove(KEY_AUTH_TYPE)
        editor.apply()
    }
}