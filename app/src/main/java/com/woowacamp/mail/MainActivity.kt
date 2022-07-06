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

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    private var nicknameValid = false
    private var emailValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            nicknameEdit.addTextChangedListener {
                nicknameValid = if (it == null || it.length < 4) {
                    nicknameLayout.error = getString(R.string.warn_nickname)
                    false
                } else {
                    nicknameLayout.error = null
                    true
                }
                nextButton.isEnabled = nicknameValid && emailValid
            }
            emailEdit.addTextChangedListener {
                val regex = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$".toRegex()
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
                startEmailActivity()
            }
        }
    }

    private fun startEmailActivity() {
//        startActivity(
//            Intent()
//        )
    }
}