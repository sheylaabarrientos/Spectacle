package com.example.spectacle

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.spectacle.ui.login.FormLogin
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

//        supportActionBar!!.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            openScreenLogin()
        }, 2000)
    }

    private fun openScreenLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}
