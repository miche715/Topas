package com.example.android.contact.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.viewmodel.ContactViewModel
import com.example.android.databinding.ActivityContactBinding
import com.example.android.user.view.UserSettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : BaseActivity<ActivityContactBinding>(R.layout.activity_contact)
{
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onInitialize()
    {
        setToolBar(binding.toolBar)

        binding.bottomNavigationView.setOnItemSelectedListener()
        {
            replaceFragment(
                when(it.itemId)
                {
                    R.id.member -> MemberContactFragment()
                    else -> TeamContactFragment()
                })
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.menu_toolbar_contact, menu)

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
        }

        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
            .replace(binding.memberTeamFragmentContainerView.id, fragment)
            .commit()
    }
}
