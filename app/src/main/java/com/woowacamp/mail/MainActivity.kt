package com.woowacamp.mail

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.woowacamp.mail.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        private const val NICKNAME_KEY = "NICKNAME_KEY"
        private const val EMAIL_KEY = "EMAIL_KEY"
    }

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    private var nicknameValid = false
    private var emailValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            if (savedInstanceState != null) {
                nicknameEdit.setText(savedInstanceState.getString(NICKNAME_KEY))
                emailEdit.setText(savedInstanceState.getString(EMAIL_KEY))
            }

            nicknameEdit.addTextChangedListener {
                val regex1 = "^(?=.*?[a-zA-Z])(?=.*?[0-9]).{4,}$".toRegex()
                val regex2 = "^[a-zA-Z0-9]{4,}$".toRegex()
                nicknameValid = if (it == null || !it.matches(regex1) || !it.matches(regex2)) {
                    nicknameLayout.error = getString(R.string.warn_nickname)
                    false
                } else {
                    nicknameLayout.error = null
                    true
                }
                nextButton.isEnabled = nicknameValid && emailValid
            }
            emailEdit.addTextChangedListener {
                val regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$".toRegex()
                emailValid = if (it == null || !it.matches(regex)) {
                    emailLayout.error = getString(R.string.warn_email)
                    false
                } else {
                    emailLayout.error = null
                    true
                }
                nextButton.isEnabled = nicknameValid && emailValid
            }

            nextButton.setOnClickListener {
                startHomeActivity(
                    nicknameEdit.text.toString(),
                    emailEdit.text.toString()
                )
            }
        }
    }

    /**
     * ????????? ??????????????? ????????? ?????? ??????
     * ?????? : EditText ??? ?????? ?????? ????????? ????????? ?????????
     */
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putString(NICKNAME_KEY, binding.nicknameEdit.text.toString())
//        outState.putString(EMAIL_KEY, binding.emailEdit.text.toString())
//    }

    /**
     * ???????????? ????????? ????????? ?????? ????????? ?????? ????????? flag ??????
     * ACTIVITY_CLEAR_TASK :: ?????? ???????????? ??? ???????????? ?????? ??????
     * ACTIVITY_NEW_TASK :: ?????? ??????????????? ????????? ???????????? ??? ??????????????? ??????
     */
    private fun startHomeActivity(nickname: String?, email: String?) {
        startActivity(
            Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(HomeActivity.NICKNAME_EXTRA, nickname)
                putExtra(HomeActivity.EMAIL_EXTRA, email)
            }
        )
    }
}