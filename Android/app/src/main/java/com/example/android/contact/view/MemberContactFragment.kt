package com.example.android.contact.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        contactViewModel.initializeLoadMemberListQuery()
        loadMember()

        binding!!.swipeRefreshLayout.setOnRefreshListener()  // 맨 위로 스크롤 하면 새로고침
        {
            memberAdapter.clearMemberList()  // 어댑터의 리스트를 비움
            contactViewModel.initializeLoadMemberListQuery()  // 쿼리를 초기로 돌림
            loadMember()  // 리스트 로딩

            binding!!.swipeRefreshLayout.isRefreshing = false
        }

        binding!!.memberRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener()  // 리사이클러뷰의 스크롤 이벤트 감지
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount

                if(lastVisibleItemPosition + 1 == itemTotalCount)  // 리사이클러뷰가 현재 목록의 끝에 닿으면
                {
                    loadMember()
                }
            }
        })

        contactViewModel.loadMemberListResult.observe(this@MemberContactFragment.activity!!)
        {
            memberAdapter.addMemberList(it)
        }
    }

    private fun loadMember()
    {
        contactViewModel.loadMemberList()
    }
}