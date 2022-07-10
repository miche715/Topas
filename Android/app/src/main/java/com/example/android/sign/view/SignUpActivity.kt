package com.example.android.sign.view

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivitySignUpBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.example.android.user.domain.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) })
{
    private val signViewModel: SignViewModel by viewModels()

    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    private var profilePhoto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding.profilePhotoImageButton.setOnClickListener()
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
            }
        }

        binding.signUpButton.setOnClickListener()
        {
            hideKeyBoard(it.windowToken)

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
            if(it is User)  // 회원가입 성공
            {
                println(it)
            }
            else  // 회원가입 실패
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
        }
        signViewModel.emailInValidMessage.observe(this)
        {
            if(it != null) { binding.emailErrorTextView.visibility = View.VISIBLE; binding.emailErrorTextView.text = it }
            else { binding.emailErrorTextView.visibility = View.GONE; binding.emailErrorTextView.text = null }
        }
        signViewModel.passwordInValidMessage.observe(this)
        {
            if(it != null) { binding.passwordErrorTextView.visibility = View.VISIBLE; binding.passwordErrorTextView.text = it }
            else { binding.passwordErrorTextView.visibility = View.GONE; binding.passwordErrorTextView.text = null }
        }
        signViewModel.passwordConfirmInValidMessage.observe(this)
        {
            if(it != null) { binding.passwordConfirmErrorTextView.visibility = View.VISIBLE; binding.passwordConfirmErrorTextView.text = it }
            else { binding.passwordConfirmErrorTextView.visibility = View.GONE; binding.passwordConfirmErrorTextView.text = null }
        }
        signViewModel.nameInValidMessage.observe(this)
        {
            if(it != null) { binding.nameErrorTextView.visibility = View.VISIBLE; binding.nameErrorTextView.text = it }
            else { binding.nameErrorTextView.visibility = View.GONE; binding.nameErrorTextView.text = null }
        }
        signViewModel.nickNameInValidMessage.observe(this)
        {
            if(it != null) { binding.nickNameErrorTextView.visibility = View.VISIBLE; binding.nickNameErrorTextView.text = it }
            else { binding.nickNameErrorTextView.visibility = View.GONE; binding.nickNameErrorTextView.text = null }
        }
    }
}