package com.example.exercise_22.presentation.firebase

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.exercise_22.R
import com.example.exercise_22.presentation.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService :  FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

        super.onMessageReceived(message)
        message.data
        println(message.data)
        showNotification(message.data["id"] ?: "", message.data["title"] ?: "", message.data["desc"] ?: "")
    }

    private fun showNotification(id: String, title: String, desc: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("postId", id)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        val notification = NotificationCompat.Builder(applicationContext, "firebase_messaging_id")
            .setContentTitle(title)
            .setContentText(desc)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            NotificationManagerCompat.from(applicationContext)
                .notify(id.toInt(), notification)
        }
    }
}