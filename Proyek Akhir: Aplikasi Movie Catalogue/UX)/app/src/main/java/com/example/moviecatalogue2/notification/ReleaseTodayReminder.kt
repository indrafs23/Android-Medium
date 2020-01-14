package com.example.moviecatalogue2.notification

import android.R
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.moviecatalogue2.data.ListFilm
import com.example.moviecatalogue2.data.TmdbApi
import com.example.moviecatalogue2.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class ReleaseTodayReminder : BroadcastReceiver() {
    private var notifId = 1000
    private fun sendNotification(
        context: Context,
        title: String,
        mDesc: String,
        id: Int
    ) {
        val mNotificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        val mIntent = Intent(context, MainActivity::class.java)

        val mPendingIntent = PendingIntent.getActivity(
            context, id, mIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(mDesc)
            .setContentIntent(mPendingIntent)
            .setColor(ContextCompat.getColor(context, R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(uriTone)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationChannel = NotificationChannel(
                "11001", "NOTIFICATION_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_HIGH
            )
            mNotificationChannel.enableLights(true)
            mNotificationChannel.lightColor = Color.YELLOW
            mNotificationChannel.enableVibration(true)
            mNotificationChannel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            builder.setChannelId("11001")
            mNotificationManager.createNotificationChannel(mNotificationChannel)
        }
        mNotificationManager.notify(id, builder.build())
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, ReleaseTodayReminder::class.java)
        return PendingIntent.getBroadcast(context, 1001, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun setAlarm(context: Context, mMovieResults: ListFilm) {
        cancelAlarm(context)
        var delay = 0
        Log.d("Masuk", "Masuk 4")
        for (movies in mMovieResults.results) {
            Log.d("Masuk", "Masuk 3")
            val mAlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            var intent = Intent(context, ReleaseTodayReminder::class.java)
            intent.putExtra("title", movies.title)
            intent.putExtra("id", notifId)
            Log.d("Masuk", movies.title.toString() + "  " + notifId.toString())
            var pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    notifId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

            val mCalendar = Calendar.getInstance()
            mCalendar.set(Calendar.HOUR_OF_DAY, 11)
            mCalendar.set(Calendar.MINUTE, 11)
            mCalendar.set(Calendar.SECOND, 0)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                mAlarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    mCalendar.getTimeInMillis() + delay,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    mCalendar.getTimeInMillis() + delay,
                    pendingIntent
                )
            }
            notifId++
            delay += 5000
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val mMovieTitle = intent.getStringExtra("title")
        val id = intent.getIntExtra("id", 0)
        val mDesc = "Movie just released" + " " + mMovieTitle
        sendNotification(context, "Catalogue Movies", mDesc, id)
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
    }

    fun getData(context: Context) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        val today = dateFormat.format(date)
        var result = ListFilm()

        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(TmdbApi::class.java)
        val call = movieApi.getUpcomingMovies(TmdbApi.API_KEY, today, today)
        call.enqueue(object : Callback<ListFilm> {
            override fun onFailure(call: Call<ListFilm>, t: Throwable) {
                val toast = Toast.makeText(
                    context,
                    com.example.moviecatalogue2.R.string.connection,
                    Toast.LENGTH_LONG
                )
                toast.show()
            }

            override fun onResponse(call: Call<ListFilm>, response: Response<ListFilm>) {
                if (response.code() == 200) {
                    val results = response.body() as ListFilm
                    result = results
                    Log.d("Masuk", "Masuk 2")
                    setAlarm(context, results)
                }
            }
        })
    }
}