package com.example.android.utility

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("profilePhotoUrl")
fun bindImageFromUrl(view: CircleImageView, profilePhotoUrl: String?)
{
    if (!profilePhotoUrl.isNullOrEmpty())
    {
        Glide.with(view.context).load(profilePhotoUrl).into(view)
    }
    else
    {
        Glide.with(view.context).load(R.drawable.default_profile_photo).into(view)
    }
}
