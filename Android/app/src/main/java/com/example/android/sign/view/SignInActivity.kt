package com.example.android.sign.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivitySignInBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.example.android.utility.LoadingDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in)
{
    private val signViewModel: SignViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@SignInActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar)

        binding.signViewModel = signViewModel
        binding.signInActivity = this@SignInActivity

        checkInternetPermission()

        signViewModel.signInResult.observe(this)
        {
            if((it is Boolean) && it)  // 로그인 성공 - Boolean, 로그인 실패 - String
            {
                Intent(this@SignInActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
            else
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            loadingDialog.dismiss()
        }
    }

    fun signIn(view: View)
    {
        hideKeyBoard(view.windowToken)
        loadingDialog.show()

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

    private fun checkInternetPermission()
    {
        val internetPermission = ContextCompat.checkSelfPermission(this@SignInActivity, Manifest.permission.INTERNET)

        if(internetPermission != PackageManager.PERMISSION_GRANTED)
        {
            requestInternetPermission()
        }
    }

    private fun requestInternetPermission()
    {
        ActivityCompat.requestPermissions(this@SignInActivity, arrayOf(Manifest.permission.INTERNET), 1000)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        when(requestCode)
        {
            1000 ->  if(grantResults[0] != PackageManager.PERMISSION_GRANTED) { finishAffinity() }
        }
    }
}