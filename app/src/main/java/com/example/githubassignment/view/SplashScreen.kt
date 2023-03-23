package com.example.githubassignment.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.githubassignment.R

class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i)

            //the current activity will get finished.
        }, 2000)

    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }

}