package com.woowacamp.mail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.woowacamp.mail.data.Mail
import com.woowacamp.mail.fragment.MailFragment
import com.woowacamp.mail.fragment.SettingFragment

class FragmentAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val fa = fa

    private val mails = mutableListOf<Mail>()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MailFragment(mails)
            1 -> SettingFragment()
            else -> MailFragment(mails)
        }
    }
}