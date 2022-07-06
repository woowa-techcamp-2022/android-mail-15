package com.woowacamp.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    companion object {
        const val NICKNAME_EXTRA = "NICKNAME_EXTRA"
        const val EMAIL_EXTRA = "EMAIL_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}