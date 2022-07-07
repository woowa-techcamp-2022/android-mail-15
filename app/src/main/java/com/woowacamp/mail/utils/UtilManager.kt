package com.woowacamp.mail.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.woowacamp.mail.R
import java.util.*

class UtilManager { companion object {
    private val rand = Random()

    fun randomColorImage(context: Context): GradientDrawable {
        val drawable: GradientDrawable? = ContextCompat.getDrawable(context, R.drawable.round_view) as GradientDrawable?
        drawable?.setColor(randomColor(context))

        return drawable!!
    }

    fun randomColor(context: Context): Int {
        val colorArr = arrayOf(R.color.profile1, R.color.profile2, R.color.profile3, R.color.profile4, R.color.profile5)

        return colorArr[rand.nextInt(5)]
    }
} }