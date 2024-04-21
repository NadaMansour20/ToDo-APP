package com.android.todo_app.project


import android.content.Context

object LanguagePreferenceHelper {

    private const val LANGUAGE_PREF_KEY = "app_language"
    private const val LANGUAGE_PREF_FILE_NAME = "AppLanguagePrefs"

    fun saveLanguage(context: Context, language: String) {
        val sharedPreferences =
            context.getSharedPreferences(LANGUAGE_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LANGUAGE_PREF_KEY, language)
        editor.apply()
    }

    fun getLanguage(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences(LANGUAGE_PREF_FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LANGUAGE_PREF_KEY, "en") ?: "en"
    }


    private const val MODE_PREF_KEY = "selected_mode"
    private const val MODE_PREF_FILE_NAME = "AppModePrefs"

    fun saveMode(context: Context, mode: Int) {
        val sharedPreferences =
            context.getSharedPreferences(MODE_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(MODE_PREF_KEY, mode)
        editor.apply()
    }

    fun getMode(context: Context): Int {
        val sharedPreferences =
            context.getSharedPreferences(MODE_PREF_FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(MODE_PREF_KEY, 0)
    }
}
