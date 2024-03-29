package com.example.android.user.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentUserSkillBinding
import com.example.android.user.adapter.UserHaveSkillAdapter
import com.example.android.user.adapter.UserSearchSkillAdapter
import com.example.android.user.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSkillFragment : BaseFragment<FragmentUserSkillBinding>(R.layout.fragment_user_skill)
{
    private val userViewModel: UserViewModel by activityViewModels()  // 자신의 부모인 UserSettingActivity와 뷰모델 공유

    private val userSearchSkillAdapter: UserSearchSkillAdapter by lazy { UserSearchSkillAdapter(this@UserSkillFragment) }  // 터치 이벤트 때문에 자신을 넘겨줘야 함
    private val userHaveSkillAdapter: UserHaveSkillAdapter by lazy { UserHaveSkillAdapter(this@UserSkillFragment) }  // 터치 이벤트 때문에 자신을 넘겨줘야 함

    override fun onInitialize()
    {
        binding!!.mySkillRecyclerView.adapter = userHaveSkillAdapter
        binding!!.mySkillRecyclerView.layoutManager = GridLayoutManager(this@UserSkillFragment.context, 3)

        binding!!.searchSkillRecyclerView.adapter = userSearchSkillAdapter
        binding!!.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this@UserSkillFragment.context)

        binding!!.userViewModel = userViewModel
        binding!!.userSkillFragment = this@UserSkillFragment

        binding!!.skillEditText.isFocusableInTouchMode = true
        binding!!.skillEditText.requestFocus()

        userViewModel.initializeSearchErrorMessage()

        userViewModel.searchSkillResult.observe(this@UserSkillFragment.activity!!)
        {
            userSearchSkillAdapter.setSearchSkillList(it)
        }

        userViewModel.mySkillResult.observe(this@UserSkillFragment.activity!!)
        {
            userHaveSkillAdapter.setMySkillList(it)
        }
    }

    fun onSkillEditTextChange()
    {
        userViewModel.searchSkill(binding!!.skillEditText.text.toString())
    }

    fun onSkillTextViewOrRemoveSkillImageButtonClick(skill: String)
    {
        userViewModel.updateMySkill(skill)
        binding!!.skillEditText.text = null
    }

    fun onCompleteSkillButtonClick()
    {
        userViewModel.completeMySkill()
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
            .remove(this@UserSkillFragment).commit()
    }

    fun onCancelSkillButtonClick()
    {
        userViewModel.cancelMySkill()
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
            .remove(this@UserSkillFragment).commit()
    }
}