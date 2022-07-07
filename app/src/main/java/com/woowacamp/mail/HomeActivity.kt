package com.woowacamp.mail

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.woowacamp.mail.adapter.FragmentAdapter
import com.woowacamp.mail.databinding.ActivityHomeBinding
import com.woowacamp.mail.viewmodel.MailViewModel
import com.woowacamp.mail.viewmodel.MailViewModelFactory

class HomeActivity : AppCompatActivity() {
    companion object {
        const val NICKNAME_EXTRA = "NICKNAME_EXTRA"
        const val EMAIL_EXTRA = "EMAIL_EXTRA"
    }

    private var _binding: ActivityHomeBinding? = null
    private val binding: ActivityHomeBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: MailViewModel

    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MailViewModelFactory())[MailViewModel::class.java]
        adapter = FragmentAdapter(this)

        binding.apply {
            adapter.updateType(0)
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

            viewModel.type.observe(this@HomeActivity) {
                adapter.updateType(it)
            }
            primaryBtn.setOnClickListener {
                viewModel.changeType(0)
                drawer.closeDrawer(leftDrawer)
            }
            socialBtn.setOnClickListener {
                viewModel.changeType(1)
                drawer.closeDrawer(leftDrawer)
            }
            promotionBtn.setOnClickListener {
                viewModel.changeType(2)
                drawer.closeDrawer(leftDrawer)
            }

            menu.setOnClickListener {
                drawer.openDrawer(leftDrawer)
            }
        }
    }

    /**
     * configuration 변경을 감지하여 xml 파일을 직접 바꿔보려 했지만
     * 바인딩 문제로 코드 구조가 복잡해 질 우려 있어, 일단 보류
     * 다른 방법 강구
     */
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        if (newConfig.screenWidthDp >= 600) {
//            setContentView(R.layout.activity_home_wide)
//        } else {
//            setContentView(R.layout.activity_home)
//        }
//    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(binding.leftDrawer)) {
            binding.drawer.closeDrawer(binding.leftDrawer)
        } else if (binding.tabView.selectedTabPosition != 0 || viewModel.type.value != 0) {
            viewModel.changeType(0)
            binding.tabView.getTabAt(0)?.select()
        } else {
            super.onBackPressed()
        }
    }
}