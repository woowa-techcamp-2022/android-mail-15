package com.woowacamp.mail.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.woowacamp.mail.R
import com.woowacamp.mail.data.Mail
import com.woowacamp.mail.databinding.ItemMailBinding
import com.woowacamp.mail.utils.UtilManager
import java.util.*

class MailAdapter(): RecyclerView.Adapter<MailViewHolder>() {

    private val mails = mutableListOf<Mail>()

    fun updateList(mails: List<Mail>) {
        this.mails.clear()
        this.mails.addAll(mails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mail, parent, false)

        return MailViewHolder(ItemMailBinding.bind(view), parent.context)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        val mail = mails[position]
        holder.bind(mail)
    }

    override fun getItemCount(): Int = mails.size
}

class MailViewHolder(private val binding: ItemMailBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Mail) {
        binding.apply {
            if (data.name.matches("^[ㄱ-ㅎ가-힣][ㄱ-ㅎ가-힣a-zA-Z0-9]*$".toRegex())) {
                firstname.visibility = View.INVISIBLE
                profileImg.visibility = View.VISIBLE
            } else {
                firstname.text = data.name[0].toString()
                firstname.visibility = View.VISIBLE
                profileImg.visibility = View.INVISIBLE
            }

            profileBg.setColorFilter(context.getColor(UtilManager.randomColor(context)))

            nameText.text = data.name
            titleText.text = data.title
            contentText.text = data.content
        }
    }
}