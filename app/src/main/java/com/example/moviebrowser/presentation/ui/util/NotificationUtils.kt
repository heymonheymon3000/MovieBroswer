package com.example.moviebrowser.presentation.ui.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.MainActivity

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

    val movieImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logo)

    val bigPicStyle = NotificationCompat.BigPictureStyle().bigPicture(movieImage).bigLargeIcon(null)

    val builder = NotificationCompat.Builder(applicationContext,
            applicationContext.getString(R.string.movie_refresh_worker_notification_channel_id))
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(applicationContext
                    .getString(R.string.notification_title))
            .setContentText(messageBody)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setStyle(bigPicStyle)
            .setLargeIcon(movieImage)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}