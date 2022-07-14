package com.example.android.user.view

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.android.databinding.ActivityUserSkillBinding
import com.example.android.user.adapter.MySkillAdapter
import com.example.android.user.adapter.SearchSkillAdapter
import com.example.android.user.viewmodel.UserViewModel

@AndroidEntryPoint
class UserSkillActivity : BaseActivity<ActivityUserSkillBinding>(R.layout.activity_user_skill)
{
    private val userViewModel: UserViewModel by viewModels()

    private val searchSkillAdapter: SearchSkillAdapter by lazy { SearchSkillAdapter(this@UserSkillActivity) }
    private val mySkillAdapter: MySkillAdapter by lazy { MySkillAdapter(this@UserSkillActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.mySkillRecyclerView.adapter = mySkillAdapter
        binding.mySkillRecyclerView.layoutManager = GridLayoutManager(this, 3)

        binding.searchSkillRecyclerView.adapter = searchSkillAdapter
        binding.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.userViewModel = userViewModel
        binding.userSkillActivity = this@UserSkillActivity

        userViewModel.searchSkillResult.observe(this)
        {
            searchSkillAdapter.setSearchSkillList(it)
        }

        userViewModel.mySkillResult.observe(this)
        {
            mySkillAdapter.setMySkillList(it)
            println(it)
        }
    }

    fun searchSkill()
    {
        userViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String)
    {
        userViewModel.updateMySkill(skill)
        binding.skillEditText.text = null
    }

    fun complete()
    {
        
    }
}