package com.example.android.sign.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.android.R

class SignLoadingDialog(context: Context) : Dialog(context)
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_loading_dialog)

        setCancelable(false)  // 취소 불가능

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))  // 다이얼로그의 배경 투명하게 바꿔줌
    }
}