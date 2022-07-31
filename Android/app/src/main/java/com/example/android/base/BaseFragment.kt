package com.example.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes val layoutRes: Int): Fragment()  // 프래그먼트에 공통적으로 들어가는 뷰·데이터 바인딩 코드나 메서드들을 가지고 있음
{
    protected var binding: T? = null

    protected abstract fun onInitialize()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = viewLifecycleOwner

        onInitialize()
    }

    override fun onDestroyView()
    {
        super.onDestroyView()

        binding = null
    }
}