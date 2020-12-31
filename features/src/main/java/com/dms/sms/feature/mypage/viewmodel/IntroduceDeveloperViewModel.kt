package com.dms.sms.feature.mypage.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.mypage.model.DeveloperModel
import com.dms.sms.util.DeveloperList

class IntroduceDeveloperViewModel : BaseViewModel() {
    val developerList = MutableLiveData<ArrayList<DeveloperModel>>().apply {
        value = ArrayList(emptyList())
    }

    init {
        developerList.value = DeveloperList.getDeveloperList()
    }
}