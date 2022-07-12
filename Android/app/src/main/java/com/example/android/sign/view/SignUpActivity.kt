package com.example.android.sign.view

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.databinding.ActivitySignUpBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up)
{
    private val signViewModel: SignViewModel by viewModels()

    private val signLoadingDialog: SignLoadingDialog by lazy { SignLoadingDialog(this@SignUpActivity) }

    private var profilePhoto: Uri? = null

    private var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode == RESULT_OK)
        {
            profilePhoto = it.data!!.data
            binding.profilePhotoCircleImageView.setImageURI(profilePhoto)

            binding.addProfilePhotoButton.isEnabled = false
            binding.removeProfilePhotoButton.isEnabled = true
        }
    }

    override fun onInitialize()
    {
        setSupportActionBar(binding.toolBar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.signViewModel = signViewModel  // xml 파일에 signViewModel이라는 변수에 그 변수가 사용하는 파일을 바인딩, 이 한줄 때문에 얼마나 삽질을 한거지?
        binding.signUpActivity = this@SignUpActivity  // xml 파일에 signUpActivity이라는 변수에 그 변수가 사용하는 파일을 바인딩

        signViewModel.signUpResult.observe(this)
        {
            if((it is Boolean) && it)  // 회원가입 성공
            {
//                Intent(this@SignUpActivity, SignInActivity::class.java).run()
//                {
//                    startActivity(this)
//
//                    finish()
//                }
                println(currentUser)
            }
            else  // 회원가입 실패
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            signLoadingDialog.dismiss()
        }
    }

    fun addProfilePhoto()  // 프로필 사진 선택
    {
        Intent(Intent.ACTION_GET_CONTENT).run()
        {
            this.type = "image/*"
            activityResultLauncher.launch(this)
        }
    }

    fun removeProfilePhoto()  // 선택한 프로필 사진을 제거
    {
        profilePhoto = null
        binding.profilePhotoCircleImageView.setImageResource(R.drawable.default_profile_photo)

        binding.addProfilePhotoButton.isEnabled = true
        binding.removeProfilePhotoButton.isEnabled = false
    }

    fun signUp(view: View)  // 회원가입
    {
        hideKeyBoard(view.windowToken)
        signLoadingDialog.show()

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val passwordConfirm = binding.passwordConfirmEditText.text.toString()
        val name = binding.nameEditText.text.toString()
        val nickName = binding.nickNameEditText.text.toString()
        val profilePhoto = profilePhoto

        signViewModel.signUp(email, password, passwordConfirm, name, nickName, profilePhoto)
    }
}