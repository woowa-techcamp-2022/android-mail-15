package com.woowacamp.mail

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.woowacamp.mail.databinding.ActivityHomeBinding
import com.woowacamp.mail.databinding.ActivityHomeWideBinding

class HomeActivity : AppCompatActivity() {
    companion object {
        const val NICKNAME_EXTRA = "NICKNAME_EXTRA"
        const val EMAIL_EXTRA = "EMAIL_EXTRA"
    }

    private var _binding: ActivityHomeBinding? = null
    private val binding: ActivityHomeBinding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.screenWidthDp >= 600) {
            setContentView(R.layout.activity_home_wide)
        } else {
            setContentView(R.layout.activity_home)
        }
    }
}