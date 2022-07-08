package com.example.android.sign.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivitySignUpBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) })
{
    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding.signUpButton.setOnClickListener()
        {
            hideKeyBoard(it.windowToken)

            if(binding.emailTextInputEditText.text!!.isNotEmpty() && binding.passwordTextInputEditText.text!!.isNotEmpty())
            {
                signViewModel.signUp(binding.emailTextInputEditText.text.toString(), binding.passwordTextInputEditText.text.toString())
            }

            if(binding.emailTextInputEditText.text!!.isEmpty()) { binding.emailTextInputLayout.error = "이메일을 입력해 주세요." }
            else { binding.emailTextInputLayout.error = null }

            if(binding.passwordTextInputEditText.text!!.isEmpty()) { binding.passwordTextInputLayout.error = "패스워드를 입력해 주세요." }
            else { binding.passwordTextInputLayout.error = null }

        }
        signViewModel.signUpResult.observe(this)
        {
            if(it)  // 회원가입 성공
            {
//                Intent(this@SignUpActivity, UserDetailActivity::class.java).run()
//                {
//                    startActivity(this)
//                }
            }
            else  // 회원가입 실패
            {
                Snackbar.make(binding.root, "이미 가입된 이메일입니다.", Snackbar.LENGTH_SHORT).show()
            }
        }


    }
}