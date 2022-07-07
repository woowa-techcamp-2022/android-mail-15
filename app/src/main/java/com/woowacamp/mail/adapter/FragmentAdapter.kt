package com.woowacamp.mail.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.woowacamp.mail.data.Mail
import com.woowacamp.mail.fragment.MailFragment
import com.woowacamp.mail.fragment.SettingFragment

class FragmentAdapter(private val fa: FragmentActivity): FragmentStateAdapter(fa) {

    private var type = 0
    private var nickname = ""
    private var email = ""

    @SuppressLint("NotifyDataSetChanged")
    fun updateType(type: Int) {
        this.type = type
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateInfo(nickname: String, email: String) {
        this.nickname = nickname
        this.email = email
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MailFragment(type)
            1 -> SettingFragment(nickname, email)
            else -> MailFragment(type)
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val fragment = this.fa.supportFragmentManager.findFragmentByTag("f$position")
        fragment?.let {
            if (it is MailFragment) {
                it.updateView(type)
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}