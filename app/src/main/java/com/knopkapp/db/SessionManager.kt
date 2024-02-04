package com.knopkapp.db

import android.content.Context
import android.content.SharedPreferences


class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isRegistered: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_REGISTERED, false)
        set(isRegistered) {
            sharedPreferences.edit().putBoolean(KEY_IS_REGISTERED, isRegistered).apply()
        }
    var restaurantName: String?
        get() = sharedPreferences.getString(KEY_RESTAURANT_NAME, "")
        set(restaurantName) {
            sharedPreferences.edit().putString(KEY_RESTAURANT_NAME, restaurantName).apply()
        }
    var status: String?
        get() = sharedPreferences.getString(KEY_STATUS, "") // Другие методы, если необходимо
        set(status) {
            sharedPreferences.edit().putString(KEY_STATUS, status).apply()
        }

    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_IS_REGISTERED = "isRegistered"
        private const val KEY_RESTAURANT_NAME = "restaurantName"
        private const val KEY_STATUS = "status"
    }
}