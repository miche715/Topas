package com.example.android.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.contact.repository.ContactRepository
import com.example.android.user.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel()
{
    private val _loadMemberListResult: MutableLiveData<MutableList<User>> = MutableLiveData()
    val loadMemberListResult: LiveData<MutableList<User>> = _loadMemberListResult

    fun loadMemberList()
    {
        contactRepository.loadMemberListFirebase(_loadMemberListResult)
    }

    fun initializeLoadMemberListQuery()
    {
        contactRepository.initializeLoadMemberListQuery()
    }
}