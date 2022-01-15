package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    val btn_click_me = findViewById(R.id.btn_click_me) as Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        btn_click_me.setOnClickListener {
            sendNotification()
        }

    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NOTIFICATION TITLE"
            val descriptionText = "Notification description"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel =
                NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val intent = Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0,intent, 0)

        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logo)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logo)


        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setLargeIcon(bitmapLargeIcon)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }


}

