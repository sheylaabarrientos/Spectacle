package com.example.spectacle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spectacle.databinding.ActivityMainBinding
import com.example.spectacle.ui.login.FormLogin
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToMovies()
        logout()

        supportActionBar!!.hide()
    }

    private fun goToMovies() {
        binding.myMovies.setOnClickListener {
            val intent = Intent(this, ListOfMoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun logout() {
        binding.logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            backScreenLogin()
        }
    }

    private fun backScreenLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()

        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest(
                AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE,
                GraphRequest.Callback {
                    AccessToken.setCurrentAccessToken(null)
                    LoginManager.getInstance().logOut()

                    finish()
                }
            ).executeAsync()
        }
    }
}
