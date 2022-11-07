package com.happiestminds.uidemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.*
import kotlin.concurrent.schedule

class NotificationActivity : AppCompatActivity() {

    lateinit var otpEdittext: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        otpEdittext = findViewById(R.id.otpe)
    }

    fun clickVerify(view: View){
        val otp=otpEdittext.text.toString()
        Toast.makeText(this,"verification OTP: $otp", Toast.LENGTH_LONG).show()
    }

    override fun onStop(){
        super.onStop()
        val otp = otpEdittext.text.toString()

        if (otp.isEmpty()){

                sendNotification()


        }
    }

    private fun sendNotification() {

        //1. get notification reference
        val nManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        //2. create notification object
        lateinit var builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //create channel

            val channel = NotificationChannel("myChannel", "DemoChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            nManager.createNotificationChannel(channel)
            builder=Notification.Builder(this,"myChannel")

        } else {
            val builder = android.app.Notification.Builder(this)

        }

        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("OTP Required")
        builder.setContentTitle("Please complete OTP verification")

        //action for notification selection - launch activity/service, send broadcast

        val otpIntent = Intent(this,NotificationActivity().javaClass)
        val pi = PendingIntent.getActivity(this,1,otpIntent,PendingIntent.FLAG_IMMUTABLE)

        builder.setContentIntent(pi)

        val myNotification = builder.build()
        myNotification.flags=Notification.FLAG_AUTO_CANCEL

        //3. display notification

        nManager.notify(1,myNotification)
    }


}