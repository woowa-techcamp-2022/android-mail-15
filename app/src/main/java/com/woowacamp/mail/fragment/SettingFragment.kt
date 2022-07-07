package com.woowacamp.mail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.woowacamp.mail.R

class SettingFragment: Fragment() {
    companion object {
        const val NICKNAME_ARGUMENT = "NICKNAME_ARGUMENT"
        const val EMAIL_ARGUMENT = "EMAIL_ARGUMENT"
    }

    private var nickname = ""
    private var email = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nickname = arguments?.getString(NICKNAME_ARGUMENT)?: "UNKNOWN"
        email = arguments?.getString(EMAIL_ARGUMENT)?: "UNKNOWN"
        val view = inflater.inflate(R.layout.fragment_setting, null)
        view.findViewById<TextView>(R.id.nickname_text).text = nickname
        view.findViewById<TextView>(R.id.email_text).text = email
        
        return view
    }
}