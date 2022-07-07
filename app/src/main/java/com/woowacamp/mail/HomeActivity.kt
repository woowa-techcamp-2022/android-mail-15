package com.woowacamp.mail

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.woowacamp.mail.adapter.FragmentAdapter
import com.woowacamp.mail.databinding.ActivityHomeBinding
import com.woowacamp.mail.databinding.ActivityHomeWideBinding
import com.woowacamp.mail.utils.DataManager

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

        binding.apply {
            val adapter = FragmentAdapter(this@HomeActivity)
            adapter.updateList(DataManager().parseMailList())
            adapter.updateInfo(intent.getStringExtra(NICKNAME_EXTRA)?: "", intent.getStringExtra(EMAIL_EXTRA)?: "")
            viewPager.adapter = adapter

            val tabNames = arrayOf(
                getString(R.string.mail),
                getString(R.string.setting)
            )
            val tabIcons = arrayOf(
                R.drawable.ic_mail,
                R.drawable.ic_settings
            )
            TabLayoutMediator(tabView, viewPager) { tab, pos ->
                tab.text = tabNames[pos]
                tab.setIcon(tabIcons[pos])
            }.attach()
        }
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