package com.dms.sms.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dms.sms.R


class AlarmRecevier : BroadcastReceiver(){

    private val CHANNEL_ID = "outingChannel"
    private val CHANNEL_NAME = "outingChannel"

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager!!.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
            NotificationCompat.Builder(context, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(context)
        }

        builder.apply {
            setContentTitle("외출 종료 30분 전")
            setSmallIcon(R.mipmap.ic_launcher)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_HIGH

        }

        val notification: Notification = builder.build()
        manager!!.notify(1, notification)
    }
}