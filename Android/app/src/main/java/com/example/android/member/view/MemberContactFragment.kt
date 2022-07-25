package com.example.android.member.view

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.chat.view.ChatRoomActivity
import com.example.android.member.adapter.MemberAdapter
import com.example.android.member.viewmodel.MemberViewModel
import com.example.android.databinding.FragmentMemberContactBinding
import com.example.android.user.domain.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberContactFragment : BaseFragment<FragmentMemberContactBinding>(R.layout.fragment_member_contact)
{
    private val memberViewModel: MemberViewModel by activityViewModels()

    private val memberAdapter: MemberAdapter by lazy { MemberAdapter(this@MemberContactFragment) }

    override fun onInitialize()
    {
        binding!!.memberRecyclerView.adapter = memberAdapter
        binding!!.memberRecyclerView.layoutManager = LinearLayoutManager(this@MemberContactFragment.context)

        memberViewModel.initializeLoadMemberListQuery()
        loadMember()

        binding!!.swipeRefreshLayout.setOnRefreshListener()  // 맨 위로 스크롤 하면 새로고침
        {
            memberAdapter.clearMemberList()  // 어댑터의 리스트를 비움
            memberViewModel.initializeLoadMemberListQuery()  // 쿼리를 초기로 돌림
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

        memberViewModel.loadMemberListResult.observe(this@MemberContactFragment.activity!!)
        {
            memberAdapter.addMemberList(it)
        }
    }

    private fun loadMember()
    {
        memberViewModel.loadMemberList()
    }

    fun onContactClick(member: User)
    {
        Intent(this@MemberContactFragment.context!!, ChatRoomActivity::class.java).run()
        {
            this.putExtra("destinationDocumentId", member.documentId)
            this.putExtra("destinationNickName", member.nickName)
            startActivity(this)
        }
    }
}