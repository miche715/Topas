package com.example.android.contact.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.android.member.view.MemberContactFragment
import com.example.android.member.view.MemberSearchActivity
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.chat.view.ChatRoomListFragment
import com.example.android.databinding.ActivityContactBinding
import com.example.android.team.view.TeamContactFragment
import com.example.android.team.view.TeamSearchActivity
import com.example.android.user.view.UserSettingActivity
import com.example.android.utility.NowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : BaseActivity<ActivityContactBinding>(R.layout.activity_contact)
{
    private var nowFragment = NowFragment.MEMBER

    override fun onInitialize()
    {
        setToolBar(binding.toolBar)

        binding.bottomNavigationView.setOnItemSelectedListener()
        {
            replaceFragment(
                when(it.itemId)
                {
                    R.id.member -> {
                        nowFragment = NowFragment.MEMBER
                        MemberContactFragment()
                    }
                    R.id.team -> {
                        nowFragment = NowFragment.TEAM
                        TeamContactFragment()
                    }
                    else -> {
                        nowFragment = NowFragment.CHAT
                        ChatRoomListFragment()
                    }
                })
            binding.nowFragment = nowFragment
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        if(nowFragment == NowFragment.CHAT)
        {
            menuInflater.inflate(R.menu.menu_toolbar_contact_chat, menu)
        }
        else
        {
            menuInflater.inflate(R.menu.menu_toolbar_contact, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.userSettingIcon -> {
                Intent(this@ContactActivity, UserSettingActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }

            R.id.searchIcon -> {
                when(nowFragment)
                {
                    NowFragment.MEMBER -> {
                        Intent(this@ContactActivity, MemberSearchActivity::class.java).run()
                        {
                            startActivity(this)
                        }
                    }
                    NowFragment.TEAM -> {
                        Intent(this@ContactActivity, TeamSearchActivity::class.java).run()
                        {
                            startActivity(this)
                        }
                    }
                    else -> {

                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(binding.memberTeamFragmentContainerView.id, fragment)
            .commit()
    }
}
