package com.dms.sms.feature.mypage.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dms.sms.util.GlideApp

@BindingAdapter("setProfile")
fun ImageView.setProfile(profileUri: String?) {
    if (profileUri != null) {
        GlideApp.with(context)
            .load("https://dsm-sms-s3.s3.ap-northeast-2.amazonaws.com/$profileUri")
            .into(this)
    }
}