package com.example.android.user.view

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivityUserSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.utility.LoadingDialog
import com.example.android.user.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class UserSettingActivity : BaseActivity<ActivityUserSettingBinding>(R.layout.activity_user_setting)
{
    private val userViewModel: UserViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@UserSettingActivity) }

    private var profilePhoto: Uri? = currentUser!!.profilePhotoUrl?.toUri()

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

        binding.currentUser = currentUser
        binding.userViewModel = userViewModel
        binding.userSettingActivity = this@UserSettingActivity

        userViewModel.updateUserResult.observe(this)
        {
            if((it is Boolean) && it)  // 정보 수정 성공
            {
                Intent(this@UserSettingActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
            else  // 정보 수정 실패
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            loadingDialog.dismiss()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  // 여기서만 재정의
    {
        when(item.itemId)
        {
            android.R.id.home -> {
                Intent(this@UserSettingActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
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

    fun updateUser(view: View)
    {
        hideKeyBoard(view.windowToken)
        loadingDialog.show()

        val name = binding.nameEditText.text.toString()
        val nickName = binding.nickNameEditText.text.toString()
        val profilePhoto = profilePhoto
        val introduce = binding.introduceEditText.text.toString()
        val isExposureChecked = binding.exposureCheckBox.isChecked

        userViewModel.updateUser(name, nickName, profilePhoto, introduce, isExposureChecked)
    }
}