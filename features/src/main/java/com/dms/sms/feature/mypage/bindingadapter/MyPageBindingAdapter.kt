package com.dms.sms.feature.mypage.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.mypage.adapter.DeveloperAdapter
import com.dms.sms.feature.mypage.model.DeveloperModel
import com.dms.sms.util.GlideApp

@BindingAdapter("setProfile")
fun ImageView.setProfile(profileUri: String?) {
    if (profileUri != null) {
        GlideApp.with(context)
            .load("https://dsm-sms-s3.s3.ap-northeast-2.amazonaws.com/$profileUri")
            .into(this)
    }
}

@BindingAdapter("developerList")
fun RecyclerView.bindDeveloperList(developerItems: MutableLiveData<ArrayList<DeveloperModel>>) {
    (adapter as DeveloperAdapter).setItems(developerItems)
}

@BindingAdapter("setDeveloperProfile")
fun ImageView.setDeveloperProfile(path: Int?){
    if(path != null){
        GlideApp.with(context)
            .load(path)
            .into(this)
    }
}
