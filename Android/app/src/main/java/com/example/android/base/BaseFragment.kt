package com.example.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>: Fragment()  // 프래그먼트에 공통적으로 들어가는 뷰 바인딩 코드나 동작들을 가지고 있음
{
    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = getFragmentBinding(inflater, container)

        return binding.root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()

        _binding = null
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
}