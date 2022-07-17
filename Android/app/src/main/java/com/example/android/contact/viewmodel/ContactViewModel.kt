package com.example.android.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.contact.repository.ContactRepository
import com.example.android.user.domain.User
import com.example.android.utility.skillList
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

    private val _searchSkillResult: MutableLiveData<MutableList<String>> = MutableLiveData()
    val searchSkillResult: LiveData<MutableList<String>> = _searchSkillResult
    private val tempSearchSkillList = mutableListOf<String>()

    fun searchSkill(skillString: String)
    {
        tempSearchSkillList.clear()

        if(skillString.isNotEmpty())  // 스킬을 검색하는 에디트 텍스트가 비어있지 않음
        {
            skillList.forEach()  // 스킬들을 넣어둔 리스트에서 검색 조건에 맞는 스킬들을 찾을건데
            {
                if(it.length >= skillString.length)  // 앞에서 부터 잘라서 검사할 것이므로 들어온 문자열이 스킬의 이름보다 길면 예외가 뜸
                {
                    if(skillString == it.slice(IntRange(0, skillString.length - 1)))  // 앞에서부터 그 글자랑 일치하면
                    {
                        tempSearchSkillList.add(it)  // 임시 리스트에 저장
                    }
                }
            }
            _searchSkillResult.value = tempSearchSkillList  // 저장을 다했으면 결과에 넣음, 이렇게 하지 않고 위에서 바로 넣으면 skillSearchResult가 직접 변하는게 아니라 observe가 안먹음
        }
        else
        {
            _searchSkillResult.value = mutableListOf()
        }
    }

    private val _loadMemberBySkillResult: MutableLiveData<MutableList<User>> = MutableLiveData()
    val loadMemberBySkillResult: LiveData<MutableList<User>> = _loadMemberBySkillResult
    private val _loadMemberBySkillErrorMessage: MutableLiveData<String?> = MutableLiveData()
    val loadMemberBySkillErrorMessage: LiveData<String?> = _loadMemberBySkillErrorMessage

    fun loadMemberBySkillList(skill: String)
    {
        contactRepository.loadMemberBySkillListFirebase(listOf(skill), _loadMemberBySkillResult, _loadMemberBySkillErrorMessage)
    }
}