package com.example.android.contact.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.contact.adapter.MemberAdapter
import com.example.android.contact.viewmodel.ContactViewModel
import com.example.android.databinding.FragmentMemberContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberContactFragment : BaseFragment<FragmentMemberContactBinding>(R.layout.fragment_member_contact)
{
    private val contactViewModel: ContactViewModel by activityViewModels()

    private val memberAdapter: MemberAdapter by lazy { MemberAdapter(this@MemberContactFragment) }

    override fun onInitialize()
    {
        binding!!.memberRecyclerView.adapter = memberAdapter
        binding!!.memberRecyclerView.layoutManager = LinearLayoutManager(this@MemberContactFragment.context)

        contactViewModel.loadMemberListResult.observe(this@MemberContactFragment.activity!!)
        {
            memberAdapter.setMemberList(it)
        }









    }
}