package com.woowacamp.mail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.woowacamp.mail.data.Mail
import com.woowacamp.mail.fragment.MailFragment
import com.woowacamp.mail.fragment.SettingFragment

class FragmentAdapter(private val fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val mails = mutableListOf<Mail>()

    fun updateList(mails: List<Mail>) {
        this.mails.clear()
        this.mails.addAll(mails)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MailFragment(mails)
            1 -> SettingFragment()
            else -> MailFragment(mails)
        }
    }
}