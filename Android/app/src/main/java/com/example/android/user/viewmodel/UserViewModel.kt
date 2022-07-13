package com.example.android.user.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel()
{

}