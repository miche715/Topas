package com.example.android.sign.view

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.marginTop
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.databinding.ActivitySignInBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in)
{
    private val signViewModel: SignViewModel by viewModels()

    private val signLoadingDialog: SignLoadingDialog by lazy { SignLoadingDialog(this@SignInActivity) }

    override fun onInitialize()
    {
        setSupportActionBar(binding.toolBar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.signViewModel = signViewModel
        binding.signInActivity = this@SignInActivity

        signViewModel.signInResult.observe(this)
        {
            if((it is Boolean) && it)  // 로그인 성공 - Boolean, 로그인 실패 - String
            {
                println(currentUser)
            }
            else
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            signLoadingDialog.dismiss()
        }
    }

    fun signIn(view: View)
    {
        hideKeyBoard(view.windowToken)
        signLoadingDialog.show()

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        signViewModel.signIn(email, password)
    }

    fun findEmail()
    {
        println("이메일 찾기")
    }

    fun findPassword()
    {
        println("비밀번호 찾기")
    }

    fun signUp()
    {
        Intent(this@SignInActivity, SignUpActivity::class.java).run()
        {
            startActivity(this)
        }
    }
}