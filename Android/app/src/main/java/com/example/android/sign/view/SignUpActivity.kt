package com.example.android.sign.view

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResultLauncher
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

    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    private val signLoadingDialog: SignLoadingDialog by lazy { SignLoadingDialog(this@SignUpActivity) }

    private var profilePhoto: Uri? = null

    override fun onInitialize()
    {
        setSupportActionBar(binding.toolBar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.addProfilePhotoButton.setOnClickListener()
        {
            Intent(Intent.ACTION_GET_CONTENT).run()
            {
                this.type = "image/*"
                activityResultLauncher.launch(this)
            }
        }
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK)
            {
                profilePhoto = it.data!!.data
                binding.profilePhotoCircleImageView.setImageURI(profilePhoto)

                binding.addProfilePhotoButton.isEnabled = false
                binding.removeProfilePhotoButton.isEnabled = true
            }
        }

        binding.removeProfilePhotoButton.setOnClickListener()
        {
            profilePhoto = null
            binding.profilePhotoCircleImageView.setImageResource(R.drawable.default_profile_photo)

            binding.addProfilePhotoButton.isEnabled = true
            binding.removeProfilePhotoButton.isEnabled = false
        }

        binding.signUpButton.setOnClickListener()
        {
            hideKeyBoard(it.windowToken)
            signLoadingDialog.show()

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirm = binding.passwordConfirmEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val nickName = binding.nickNameEditText.text.toString()
            val profilePhoto = profilePhoto

            signViewModel.signUp(email, password, passwordConfirm, name, nickName, profilePhoto)
        }
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
//        signViewModel.emailInValidMessage.observe(this)
//        {
//            if(it != null)
//            {
//                binding.emailErrorTextView.visibility = View.VISIBLE
//                binding.emailErrorTextView.text = it
//            }
//            else
//            {
//                binding.emailErrorTextView.visibility = View.GONE
//                binding.emailErrorTextView.text = null
//            }
//        }
        signViewModel.passwordInValidMessage.observe(this)
        {
            if(it != null)
            {
                binding.passwordErrorTextView.visibility = View.VISIBLE
                binding.passwordErrorTextView.text = it
            }
            else
            {
                binding.passwordErrorTextView.visibility = View.GONE
                binding.passwordErrorTextView.text = null
            }
        }
        signViewModel.passwordConfirmInValidMessage.observe(this)
        {
            if(it != null)
            {
                binding.passwordConfirmErrorTextView.visibility = View.VISIBLE
                binding.passwordConfirmErrorTextView.text = it
            }
            else
            {
                binding.passwordConfirmErrorTextView.visibility = View.GONE
                binding.passwordConfirmErrorTextView.text = null
            }
        }
        signViewModel.nameInValidMessage.observe(this)
        {
            if(it != null)
            {
                binding.nameErrorTextView.visibility = View.VISIBLE
                binding.nameErrorTextView.text = it
            }
            else
            { binding.nameErrorTextView.visibility = View.GONE
                binding.nameErrorTextView.text = null
            }
        }
        signViewModel.nickNameInValidMessage.observe(this)
        {
            if(it != null)
            {
                binding.nickNameErrorTextView.visibility = View.VISIBLE
                binding.nickNameErrorTextView.text = it
            }
            else
            {
                binding.nickNameErrorTextView.visibility = View.GONE
                binding.nickNameErrorTextView.text = null
            }
        }
    }
}