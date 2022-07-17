package com.example.android.contact.view

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.adapter.MemberAdapter
import com.example.android.contact.adapter.MemberSearchSkillAdapter
import com.example.android.contact.viewmodel.ContactViewModel
import com.example.android.databinding.ActivityMemberSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberSearchActivity : BaseActivity<ActivityMemberSearchBinding>(R.layout.activity_member_search)
{
    private val contactViewModel: ContactViewModel by viewModels()

    private val memberSearchSkillAdapter: MemberSearchSkillAdapter by lazy { MemberSearchSkillAdapter(this@MemberSearchActivity) }
    private val memberAdapter: MemberAdapter by lazy { MemberAdapter(this@MemberSearchActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchSkillRecyclerView.adapter = memberSearchSkillAdapter
        binding.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.memberRecyclerView.adapter = memberAdapter
        binding.memberRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.contactViewModel = contactViewModel
        binding.memberSearchActivity = this@MemberSearchActivity

        contactViewModel.searchSkillResult.observe(this@MemberSearchActivity)
        {
            memberSearchSkillAdapter.setSearchSkillList(it)
        }

        contactViewModel.loadMemberBySkillResult.observe(this@MemberSearchActivity)
        {
            memberAdapter.addMemberList(it)
        }
    }

    fun searchSkill()
    {
        contactViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String, view: View)
    {
        hideKeyBoard(view.windowToken)
        binding.skillEditText.text = null
        memberAdapter.clearMemberList()
        loadMember(skill)
    }

    private fun loadMember(skill: String)
    {
        contactViewModel.loadMemberBySkillList(skill)
    }
}