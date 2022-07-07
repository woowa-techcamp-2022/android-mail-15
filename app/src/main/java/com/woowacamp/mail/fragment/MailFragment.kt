package com.woowacamp.mail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.woowacamp.mail.R
import com.woowacamp.mail.adapter.MailAdapter
import com.woowacamp.mail.data.Mail

class MailFragment(private val mails: List<Mail>): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mail, null)
        val listView = view.findViewById<RecyclerView>(R.id.mail_list)
        val adapter = MailAdapter()
        adapter.updateList(mails)
        listView.adapter = adapter

        return view
    }
}