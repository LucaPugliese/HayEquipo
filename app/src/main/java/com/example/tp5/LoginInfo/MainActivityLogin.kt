package com.example.tp5.LoginInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp5.R

class MainActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        supportActionBar?.hide()

    }
}