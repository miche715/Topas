package com.example.android.sign.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivitySignUpBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.example.android.user.view.UserSettingActivity
import com.example.android.utility.LoadingDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up)
{
    private val signViewModel: SignViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@SignUpActivity) }

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
        setToolBar(binding.toolBar, true)

        binding.signViewModel = signViewModel  // xml 파일에 signViewModel이라는 변수에 그 변수가 사용하는 파일을 바인딩, 이 한줄 때문에 얼마나 삽질을 한거지?
        binding.signUpActivity = this@SignUpActivity  // xml 파일에 signUpActivity이라는 변수에 그 변수가 사용하는 파일을 바인딩

        signViewModel.signUpResult.observe(this@SignUpActivity)
        {
            if((it is Boolean) && it)  // 회원가입 성공
            {
                Intent(this@SignUpActivity, UserSettingActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
            else  // 회원가입 실패
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            loadingDialog.dismiss()
        }
    }

    private fun checkReadExternalStoragePermission()
    {
        if(ContextCompat.checkSelfPermission(this@SignUpActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this@SignUpActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
        }
        else
        {
            addProfilePhoto()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        when(requestCode)
        {
            1000 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    addProfilePhoto()
                }
            }
        }
    }

    private fun addProfilePhoto()
    {
        Intent(Intent.ACTION_GET_CONTENT).run()
        {
            this.type = "image/*"
            activityResultLauncher.launch(this)
        }
    }

    fun onAddProfilePhotoButtonClick()  // 프로필 사진 선택
    {
        checkReadExternalStoragePermission()
    }

    fun onRemoveProfilePhotoButtonClick()  // 선택한 프로필 사진을 제거
    {
        profilePhoto = null
        binding.profilePhotoCircleImageView.setImageResource(R.drawable.default_profile_photo)

        binding.addProfilePhotoButton.isEnabled = true
        binding.removeProfilePhotoButton.isEnabled = false
    }

    fun onSignUpButtonClick(view: View)  // 회원가입
    {
        hideKeyBoard(view.windowToken)
        loadingDialog.show()

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val passwordConfirm = binding.passwordConfirmEditText.text.toString()
        val name = binding.nameEditText.text.toString()
        val nickName = binding.nickNameEditText.text.toString()
        val profilePhoto = profilePhoto

        signViewModel.signUp(email, password, passwordConfirm, name, nickName, profilePhoto)
    }
}