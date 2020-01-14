package com.example.moviecatalogue2.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
import androidx.preference.SwitchPreferenceCompat
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.ListFilm
import com.example.moviecatalogue2.notification.AppCompatPref
import com.example.moviecatalogue2.notification.DailyMovieReminder
import com.example.moviecatalogue2.notification.ReleaseTodayReminder


class SettingsActivity : AppCompatPref() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_activity)
        fragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragment() {
        val releaseTodayReminder = ReleaseTodayReminder()
        val dailyMovieReminder = DailyMovieReminder()
        val listFilm = ListFilm()

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            changeLanguange()
            val sharedPrefs = activity.getSharedPreferences("com.example.moviecatalogue2", Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()

            val btmchangeDaily : SwitchPreferenceCompat? = findPreference(getString(R.string.daily_reminder))

            if (sharedPrefs == null){
                btmchangeDaily?.isChecked = sharedPrefs?.getBoolean("PREF_SWITCH_PUSH_DAILY", true)!!
            }

            btmchangeDaily?.setOnPreferenceClickListener {
                Log.d("Notification","Login to changeDailyNotification")
                if (btmchangeDaily.isChecked){
                    editor.putBoolean("PREF_SWITCH_PUSH_DAILY",true)
                    activity?.let { it1 -> dailyMovieReminder.setAlarm(it1) }
                }else{
                    editor.putBoolean("PREF_SWITCH_PUSH_DAILY",false)
                    activity?.let { it1 -> dailyMovieReminder.cancelAlarm(it1) }
                }
                true
            }

            val btmChangeRelease : SwitchPreferenceCompat? = findPreference(getString(R.string.release_reminder))

            if(sharedPrefs == null){
                btmChangeRelease?.isChecked = sharedPrefs.getBoolean("PREF_SWITCH_PUSH_RELEASE", true)
            }
            btmChangeRelease?.setOnPreferenceClickListener {
                if (btmChangeRelease.isChecked){
                    editor.putBoolean("PREF_SWITCH_PUSH_RELEASE",true)
                    Toast.makeText(activity, "Upcoming Notif ON", Toast.LENGTH_SHORT).show()
                    activity?.let { it1 -> releaseTodayReminder.getData(it1) }
                }else{
                    editor.putBoolean("PREF_SWITCH_PUSH_RELEASE",false)
                    Toast.makeText(activity, "Upcoming Notif OFF", Toast.LENGTH_SHORT).show()
                    activity?.let { it1 -> releaseTodayReminder.cancelAlarm(it1) }
                }
                true
            }
        }

        private fun changeLanguange(){
            val btmchangeLanguange: Preference? = findPreference(getString(R.string.changeLanguange))
            btmchangeLanguange?.setOnPreferenceClickListener {
                val moveDetail = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(moveDetail)
                true
            }
        }

    }
}