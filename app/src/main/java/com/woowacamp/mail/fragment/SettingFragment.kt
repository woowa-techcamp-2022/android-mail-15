package com.woowacamp.mail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.woowacamp.mail.R

class SettingFragment(private val nickname: String, private val email: String): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, null)
        view.findViewById<TextView>(R.id.nickname_text).text = nickname
        view.findViewById<TextView>(R.id.email_text).text = email
        
        return view
    }
}