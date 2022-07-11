package com.woowacamp.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigationrail.NavigationRailView
import com.google.android.material.tabs.TabLayout
import com.woowacamp.mail.databinding.ActivityHomeBinding
import com.woowacamp.mail.fragment.MailFragment
import com.woowacamp.mail.fragment.SettingFragment
import com.woowacamp.mail.viewmodel.MailViewModel
import com.woowacamp.mail.viewmodel.MailViewModelFactory

class HomeActivity : AppCompatActivity() {
    companion object {
        const val NICKNAME_EXTRA = "NICKNAME_EXTRA"
        const val EMAIL_EXTRA = "EMAIL_EXTRA"

        const val TAB_STATE = "TAB_STATE"
        const val MAIL_TYPE_STATE = "MAIL_TYPE_STATE"
    }

    private var _binding: ActivityHomeBinding? = null
    private val binding: ActivityHomeBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: MailViewModel

    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MailViewModelFactory())[MailViewModel::class.java]

        binding.apply {
            /**
             * TabLayout 과 NavigationRailView 간의 구성 차이
             */
            var bundle = Bundle()
            bundle.putInt(MailFragment.TYPE_ARGUMENT, viewModel.type.value ?: 0)
            mailFragment.arguments = bundle

            bundle = Bundle()
            bundle.putString(SettingFragment.NICKNAME_ARGUMENT, intent.getStringExtra(NICKNAME_EXTRA)?: "UNKNOWN")
            bundle.putString(SettingFragment.EMAIL_ARGUMENT, intent.getStringExtra(EMAIL_EXTRA)?: "UNKNOWN")
            settingFragment.arguments = bundle

            if (viewModel.tab.value == 1) {
                railView?.menu?.findItem(R.id.setting_menu)?.isChecked = true
                supportFragmentManager.beginTransaction().replace(R.id.viewPager, settingFragment).commit()
            } else {
                railView?.menu?.findItem(R.id.mail_menu)?.isChecked = true
                supportFragmentManager.beginTransaction().replace(R.id.viewPager, mailFragment).commit()
            }

            tabView?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> supportFragmentManager.beginTransaction().replace(R.id.viewPager, mailFragment).commit()
                        1 -> supportFragmentManager.beginTransaction().replace(R.id.viewPager, settingFragment).commit()
                    }
                    viewModel.changeTab(tab?.position?: 0)
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
            railView?.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.mail_menu -> {
                        viewModel.changeTab(0)
                        true
                    }
                    R.id.setting_menu -> {
                        viewModel.changeTab(1)
                        true
                    }
                    else -> false
                }
            }

            viewModel.type.observe(this@HomeActivity) {
                mailFragment.updateView(it)
                drawer.closeDrawer(leftDrawer)
            }
            viewModel.tab.observe(this@HomeActivity) {
                tabView?.getTabAt(it)?.select()
                railView?.menu?.findItem(R.id.mail_menu + it)?.isChecked = true
                bundle = Bundle()
                bundle.putInt(MailFragment.TYPE_ARGUMENT, viewModel.type.value ?: 0)
                mailFragment.arguments = bundle
                if (it == 0)
                    supportFragmentManager.beginTransaction().replace(R.id.viewPager, mailFragment).commit()
                else
                    supportFragmentManager.beginTransaction().replace(R.id.viewPager, settingFragment).commit()
            }

            primaryBtn.setOnClickListener {
                viewModel.changeType(0)
            }
            socialBtn.setOnClickListener {
                viewModel.changeType(1)
            }
            promotionBtn.setOnClickListener {
                viewModel.changeType(2)
            }

            menu.setOnClickListener {
                drawer.openDrawer(leftDrawer)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TAB_STATE, binding.tabView?.selectedTabPosition?: binding.railView?.selectedItemId?.minus(R.id.mail_menu)?: 0)
        outState.putInt(MAIL_TYPE_STATE, viewModel.type.value?: 0)
    }

    override fun onBackPressed() {
        println("${viewModel.tab.value}  ${viewModel.type.value}")
        if (binding.drawer.isDrawerOpen(binding.leftDrawer)) {
            binding.drawer.closeDrawer(binding.leftDrawer)
        } else if (
            viewModel.tab.value != 0 || viewModel.type.value != 0
        ) {
            viewModel.changeType(0)
        } else {
            super.onBackPressed()
        }
    }
}