package com.woowacamp.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.woowacamp.mail.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    companion object {
        const val NICKNAME_EXTRA = "NICKNAME_EXTRA"
        const val EMAIL_EXTRA = "EMAIL_EXTRA"
    }

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}