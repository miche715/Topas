package com.example.android.member.view

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.chat.view.ChatRoomActivity
import com.example.android.member.adapter.MemberSearchSkillAdapter
import com.example.android.member.viewmodel.MemberViewModel
import com.example.android.databinding.ActivityMemberSearchBinding
import com.example.android.member.adapter.MemberSearchAdapter
import com.example.android.user.domain.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberSearchActivity : BaseActivity<ActivityMemberSearchBinding>(R.layout.activity_member_search)
{
    private val memberViewModel: MemberViewModel by viewModels()

    private val memberSearchSkillAdapter: MemberSearchSkillAdapter by lazy { MemberSearchSkillAdapter(this@MemberSearchActivity) }
    private val memberSearchAdapter: MemberSearchAdapter by lazy { MemberSearchAdapter(this@MemberSearchActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchSkillRecyclerView.adapter = memberSearchSkillAdapter
        binding.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.memberRecyclerView.adapter = memberSearchAdapter
        binding.memberRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.memberViewModel = memberViewModel
        binding.memberSearchActivity = this@MemberSearchActivity

        memberViewModel.searchSkillResult.observe(this@MemberSearchActivity)
        {
            memberSearchSkillAdapter.setSearchSkillList(it)
        }

        memberViewModel.loadMemberBySkillResult.observe(this@MemberSearchActivity)
        {
            memberSearchAdapter.addMemberList(it)
        }
    }

    fun searchSkill()
    {
        memberViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String, view: View)
    {
        hideKeyBoard(view.windowToken)
        binding.skillEditText.text = null
        memberSearchAdapter.clearMemberList()
        loadMember(skill)
    }

    private fun loadMember(skill: String)
    {
        memberViewModel.loadMemberBySkillList(skill)
    }

    fun onContactClick(member: User)
    {
        Intent(this@MemberSearchActivity, ChatRoomActivity::class.java).run()
        {
            this.putExtra("destinationDocumentId", member.documentId)
            this.putExtra("destinationNickName", member.nickName)
            this.putExtra("destinationProfilePhotoUri", member.profilePhotoUri)
            startActivity(this)
        }
    }
}