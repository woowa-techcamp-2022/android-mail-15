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
                startHomeActivity()
            }
        }
    }

    /**
     * 액티비티 스택에 전환된 화면 하나만 존재 하도록 flag 추가
     * ACTIVITY_CLEAR_TASK :: 실행 액티비티 외 스택에서 모두 제거
     * ACTIVITY_NEW_TASK :: 동일 액티비티가 스택에 존재하면 새 인스턴스로 대체
     */
    private fun startHomeActivity() {
        startActivity(
            Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}