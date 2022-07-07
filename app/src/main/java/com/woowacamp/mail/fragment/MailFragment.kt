package com.woowacamp.mail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.woowacamp.mail.R
import com.woowacamp.mail.adapter.MailAdapter
import com.woowacamp.mail.data.Mail
import com.woowacamp.mail.utils.DataManager

class MailFragment: Fragment() {
    companion object {
        const val TYPE_ARGUMENT = "TYPE_ARGUMENT"
    }

    private val typeArr = arrayOf(R.string.primary, R.string.social, R.string.promotion)
    private var type = 0
    private var adapter: MailAdapter? = null
    private var typeText: TextView? = null

    fun updateView(type: Int) {
        adapter?.updateList(DataManager().parseMailList(type))
        typeText?.text = activity?.getString(typeArr[type])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        type = arguments?.getInt(TYPE_ARGUMENT)?: 0

        val view = inflater.inflate(R.layout.fragment_mail, null)
        typeText = view.findViewById(R.id.type_text)
        val listView = view.findViewById<RecyclerView>(R.id.mail_list)
        adapter = MailAdapter()
        updateView(type)
        listView.adapter = adapter

        return view
    }
}