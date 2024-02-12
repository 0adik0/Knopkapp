package com.knopkapp.db

import android.content.Context
import android.content.SharedPreferences
import java.util.*

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
        get() = sharedPreferences.getString(KEY_STATUS, "")
        set(status) {
            sharedPreferences.edit().putString(KEY_STATUS, status).apply()
        }

    var fio: String?
        get() = sharedPreferences.getString(KEY_FIO, "")
        set(status) {
            sharedPreferences.edit().putString(KEY_FIO, status).apply()
        }

    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_IS_REGISTERED = "isRegistered"
        private const val KEY_RESTAURANT_NAME = "restaurantName"
        private const val KEY_STATUS = "status"
        private const val KEY_FIO = "fio"
        private const val LAST_VISIT_DATE_KEY = "last_visit_date"
    }

    fun isFirstTimeToday(): Boolean {
        val lastVisitDate = sharedPreferences.getLong(LAST_VISIT_DATE_KEY, 0)

        // Получаем текущую дату
        val currentDate = Calendar.getInstance()
        currentDate.set(Calendar.HOUR_OF_DAY, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)

        // Проверяем, было ли активити открыто сегодня
        val today = currentDate.timeInMillis
        return if (lastVisitDate < today) {
            // Если активити не открывалось сегодня, сохраняем текущую дату и возвращаем true
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putLong(LAST_VISIT_DATE_KEY, today)
            editor.apply()
            true
        } else {
            // Если активити уже открывалось сегодня, возвращаем false
            false
        }
    }

}
