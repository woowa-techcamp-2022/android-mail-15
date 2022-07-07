package com.woowacamp.mail.utils

import com.woowacamp.mail.data.Mail
import org.json.JSONArray
import org.json.JSONObject

class DataManager {
    val jsonStr = "[{\"name\":\"김민지\",\"title\":\"안녕하세요, 회원님\",\"content\":\"눌러보세요!\"}," +
            "{\"name\":\"minji\",\"title\":\"hey, man\",\"content\":\"Click here!\"}," +
            "{\"name\":\"지민김\",\"title\":\"hey, man\",\"content\":\"Click here!\"}," +
            "{\"name\":\"kim\",\"title\":\"hey, man\",\"content\":\"Click here!\"}]"

    fun parseMailList(): List<Mail> {
        val mails = JSONArray(jsonStr).run {
            mutableListOf<Mail>().apply {
                for (i in 0 until length()) {
                    add(parseMail(getJSONObject(i)))
                }
            }
        }

        return mails
    }

    private fun parseMail(jsonObject: JSONObject): Mail {
        return jsonObject.run {
            Mail(
                getString("name"),
                getString("title"),
                getString("content")
            )
        }
    }
}