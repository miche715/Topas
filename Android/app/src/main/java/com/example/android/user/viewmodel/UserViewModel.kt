package com.example.android.user.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel()
{
    private val nameRegex = "^[가-힣]*$".toRegex()  // 한글만
    private val nickNameRegex = "^[a-zA-Z가-힣0-9]{2,8}$".toRegex()  // 소문자, 대문자, 한글, 숫자 2 ~ 8자리

    private val _updateUserResult: MutableLiveData<Any> = MutableLiveData()
    val updateUserResult: LiveData<Any> = _updateUserResult
    val nameInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nickNameInValidMessage: MutableLiveData<String?> = MutableLiveData()

    fun updateUser(name: String, nickName: String, profilePhoto: Uri?, introduce: String, isExposureChecked: Boolean)
    {
        var isValid = true

        if(name.isEmpty())
        {
            nameInValidMessage.value = "* 이름을 입력해 주세요."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!name.matches(nameRegex))
        {
            nameInValidMessage.value = "* 잘못된 이름 형식입니다."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nameInValidMessage.value = null
        }

        if(nickName.isEmpty())
        {
            nickNameInValidMessage.value = "* 닉네임을 입력해 주세요."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!nickName.matches(nickNameRegex))
        {
            nickNameInValidMessage.value = "* 잘못된 닉네임 형식입니다."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nickNameInValidMessage.value = null
        }

        if(isValid)
        {
            userRepository.updateUserFirebase(name, nickName, profilePhoto, introduce, isExposureChecked, _updateUserResult)
        }
    }

    val skillSearchResult: MutableLiveData<MutableList<String>> = MutableLiveData()
    val skillSearchErrorMessage: MutableLiveData<String?> = MutableLiveData()
    private val tempSkillSearchList = mutableListOf<String>()

    fun searchSkill(skillString: String)
    {
        tempSkillSearchList.clear()

        if(skillString.isNotEmpty())  // 스킬을 검색하는 에디트 텍스트가 비어있지 않음
        {
            userRepository.skillList.forEach()  // 스킬들을 넣어둔 리스트에서 검색 조건에 맞는 스킬들을 찾을건데
            {
                if(it.length >= skillString.length)  // 앞에서 부터 잘라서 검사할 것이므로 들어온 문자열이 스킬의 이름보다 길면 예외가 뜸
                {
                    if(skillString == it.slice(IntRange(0, skillString.length - 1)))  // 앞에서부터 그 글자랑 일치하면
                    {
                        tempSkillSearchList.add(it)  // 임시 리스트에 저장
                    }
                }
            }
            skillSearchResult.value = tempSkillSearchList  // 저장을 다했으면 결과에 넣음. 이렇게 하지 않고 위에서 바로 넣으면 skillSearchResult가 직접 변하는게 아니라 observe가 안먹음

            if(skillSearchResult.value!!.size == 0)
            {
                skillSearchErrorMessage.value = "검색 결과가 없습니다."
            }
            else
            {
                skillSearchErrorMessage.value = null
            }
        }
        else  // 스킬을 검색하는 에디트 텍스트가 비었음
        {
            skillSearchErrorMessage.value = "스킬을 검색해 주세요."
        }
    }
}