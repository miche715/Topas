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

            val email = binding.emailTextInputEditText.text.toString()
            val password = binding.passwordTextInputEditText.text.toString()
            val passwordConfirm = binding.passwordConfirmTextInputEditText.text.toString()
            val name = binding.nameTextInputEditText.text.toString()
            val nickName = binding.nickNameTextInputEditText.text.toString()

            signViewModel.signUp(email, password, passwordConfirm, name, nickName)
        }
        signViewModel.signUpResult.observe(this)
        {
            if(it)  // 회원가입 성공
            {

            }
            else  // 회원가입 실패
            {
                Snackbar.make(binding.root, "이미 가입된 이메일입니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
        signViewModel.emailInValidMessage.observe(this)
        {
            binding.emailTextInputLayout.error = it
        }
        signViewModel.passwordInValidMessage.observe(this)
        {
            binding.passwordTextInputLayout.error = it
        }
        signViewModel.passwordConfirmInValidMessage.observe(this)
        {
            binding.passwordConfirmTextInputLayout.error = it
        }
        signViewModel.nameInValidMessage.observe(this)
        {
            binding.nameTextInputLayout.error = it
        }
        signViewModel.nickNameInValidMessage.observe(this)
        {
            binding.nickNameTextInputLayout.error = it
        }
    }
}