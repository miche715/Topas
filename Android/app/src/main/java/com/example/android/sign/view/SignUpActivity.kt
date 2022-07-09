package com.example.android.sign.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

            val email = binding.emailTextInputEditText.text.toString()
            val password = binding.passwordTextInputEditText.text.toString()
            val passwordConfirm = binding.passwordConfirmTextInputEditText.text.toString()
            val name = binding.nameTextInputEditText.text.toString()
            val nickName = binding.nickNameTextInputEditText.text.toString()
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