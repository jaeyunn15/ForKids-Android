package nh.hackaton.forkids.util

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.core.content.edit
import kotlin.time.measureTime

const val reg_number = "0102"

class AppPreference(context: Context) {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveRegno(regno: String) = pref.edit {
        putString(REGNO, regno)
    }

    fun getRegno() : String? = pref.getString(REGNO, reg_number )

    companion object {
        private const val REGNO = "regno"
    }
}