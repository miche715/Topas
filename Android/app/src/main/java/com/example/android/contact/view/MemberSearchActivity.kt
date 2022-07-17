package com.example.android.contact.view

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.adapter.MemberAdapter
import com.example.android.contact.adapter.SearchMemberSkillAdapter
import com.example.android.contact.viewmodel.ContactViewModel
import com.example.android.databinding.ActivityMemberSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberSearchActivity : BaseActivity<ActivityMemberSearchBinding>(R.layout.activity_member_search)
{
    private val contactViewModel: ContactViewModel by viewModels()

    private val searchMemberSkillAdapter: SearchMemberSkillAdapter by lazy { SearchMemberSkillAdapter(this@MemberSearchActivity) }
    private val memberAdapter: MemberAdapter by lazy { MemberAdapter(this@MemberSearchActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchSkillRecyclerView.adapter = searchMemberSkillAdapter
        binding.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.memberRecyclerView.adapter = memberAdapter
        binding.memberRecyclerView.layoutManager = LinearLayoutManager(this@MemberSearchActivity)

        binding.contactViewModel = contactViewModel
        binding.memberSearchActivity = this@MemberSearchActivity

        contactViewModel.searchSkillResult.observe(this@MemberSearchActivity)
        {
            searchMemberSkillAdapter.setSearchSkillList(it)
        }

        contactViewModel.loadMemberListForSkillResult.observe(this@MemberSearchActivity)
        {
            memberAdapter.addMemberList(it)
        }
    }

    fun searchSkill()
    {
        contactViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String)
    {
        binding.skillEditText.text = null
        memberAdapter.clearMemberList()
        loadMember(skill)
    }

    fun loadMember(skill: String)
    {
        contactViewModel.searchMemberBySkill(skill)
    }
}