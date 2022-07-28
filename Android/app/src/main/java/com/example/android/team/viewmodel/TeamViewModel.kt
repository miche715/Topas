package com.example.android.team.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.team.doamin.Team
import com.example.android.team.repository.TeamRepository
import com.example.android.utility.skillList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val teamRepository: TeamRepository) : ViewModel()
{
    //=======================================================================================================================================================================//
    private val _loadTeamListResult: MutableLiveData<MutableList<Team>> = MutableLiveData()
    val loadTeamListResult: LiveData<MutableList<Team>> = _loadTeamListResult

    fun initializeLoadTeamListQuery()
    {
        teamRepository.initializeLoadTeamListQuery()
    }

    fun loadTeamList()
    {
        teamRepository.loadTeamListFirebase(_loadTeamListResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    fun initializeLoadMyTeamListQuery()
    {
        teamRepository.initializeLoadMyTeamListQuery()
    }

    fun loadMyTeamList()
    {
        teamRepository.loadMyTeamListFirebase(_loadTeamListResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _searchTeamRequireSkillResult: MutableLiveData<MutableList<String>> = MutableLiveData()
    val searchTeamRequireSkillResult: LiveData<MutableList<String>> = _searchTeamRequireSkillResult
    private val tempSearchTeamRequireSkillResult = mutableListOf<String>()

    fun searchSkill(skillString: String)
    {
        tempSearchTeamRequireSkillResult.clear()

        if(skillString.isNotEmpty())
        {
            skillList.forEach()
            {
                if(it.length >= skillString.length)
                {
                    if(skillString == it.slice(IntRange(0, skillString.length - 1)) && it !in _selectedTeamRequireSkillResult.value!!)
                    {
                        tempSearchTeamRequireSkillResult.add(it)  // 임시 리스트에 저장
                    }
                }
            }
            _searchTeamRequireSkillResult.value = tempSearchTeamRequireSkillResult
        }
        else  // 스킬을 검색하는 에디트 텍스트가 비었음
        {
            _searchTeamRequireSkillResult.value = mutableListOf()
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _selectedTeamRequireSkillResult: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    val selectedTeamRequireSkillResult: LiveData<MutableList<String>> = _selectedTeamRequireSkillResult
    private var tempSelectedTeamRequireSkillList = mutableListOf<String>()

    fun updateRequireSkill(skill: String)
    {
        tempSelectedTeamRequireSkillList = _selectedTeamRequireSkillResult.value!!

        if(skill in tempSelectedTeamRequireSkillList)
        {
            tempSelectedTeamRequireSkillList.remove(skill)
        }
        else
        {
            tempSelectedTeamRequireSkillList.add(skill)
        }
        _selectedTeamRequireSkillResult.value = tempSelectedTeamRequireSkillList
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _loadTeamBySkillResult: MutableLiveData<MutableList<Team>> = MutableLiveData()
    val loadTeamBySkillResult: LiveData<MutableList<Team>> = _loadTeamBySkillResult
    private val _loadTeamBySkillErrorMessage: MutableLiveData<String?> = MutableLiveData()
    val loadTeamBySkillErrorMessage: LiveData<String?> = _loadTeamBySkillErrorMessage

    fun loadTeamBySkillList(skill: String)
    {
        teamRepository.loadTeamBySkillListFirebase(listOf(skill), _loadTeamBySkillResult, _loadTeamBySkillErrorMessage)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _createTeamResult: MutableLiveData<Boolean> = MutableLiveData()
    val createTeamResult: LiveData<Boolean> = _createTeamResult

    fun createTeam(title: String, explanation: String)
    {
        val skill = selectedTeamRequireSkillResult.value
        teamRepository.createTeamFirebase(title, explanation, skill, _createTeamResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _deleteTeamResult: MutableLiveData<Boolean> = MutableLiveData()
    val deleteTeamResult: LiveData<Boolean> = _deleteTeamResult

    fun deleteTeam(team: Team)
    {
        teamRepository.deleteTeamFirebase(team, _deleteTeamResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _modifyTeamResult: MutableLiveData<Boolean> = MutableLiveData()
    val modifyTeamResult: LiveData<Boolean> = _modifyTeamResult

    fun modifyTeam(title: String, explanation: String, teamDocumentId: String)
    {
        val skill = selectedTeamRequireSkillResult.value
        teamRepository.modifyTeamFirebase(title, explanation, skill, teamDocumentId, _modifyTeamResult)
    }
    //=======================================================================================================================================================================//
}