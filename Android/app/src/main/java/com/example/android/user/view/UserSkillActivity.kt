package com.example.android.user.view

import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication
import dagger.hilt.android.AndroidEntryPoint
import com.example.android.databinding.ActivityUserSkillBinding
import com.example.android.user.adapter.SearchSkillAdapter
import com.example.android.user.viewmodel.UserViewModel

@AndroidEntryPoint
class UserSkillActivity : BaseActivity<ActivityUserSkillBinding>(R.layout.activity_user_skill)
{
    private val userViewModel: UserViewModel by viewModels()

    private val searchSkillAdapter = SearchSkillAdapter()

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchSkillRecyclerView.adapter = searchSkillAdapter

        //binding.currentUser = BaseApplication.currentUser
        binding.userViewModel = userViewModel
        binding.userSkillActivity = this@UserSkillActivity

        binding.skillEditText.addTextChangedListener(object: TextWatcher
        {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                userViewModel.searchSkill(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) { }
        })
        userViewModel.skillSearchResult.observe(this)
        {
            searchSkillAdapter.setSearchSkillList(it)
        }




    }
}