package com.dms.sms.util

import com.dms.sms.R
import com.dms.sms.feature.mypage.model.DeveloperModel

object DeveloperList {
    fun getDeveloperList(): ArrayList<DeveloperModel> {
        val arrayList: ArrayList<DeveloperModel> = ArrayList()

        arrayList.add(DeveloperModel("이성진", "Frontend", R.drawable.lsj_profile))
        arrayList.add(DeveloperModel("공영길", "Frontend", R.drawable.gyg_profile))
        arrayList.add(DeveloperModel("박진홍", "Backend", R.drawable.pjh_profile))
        arrayList.add(DeveloperModel("손민기", "Backend", R.drawable.smk_profile))
        arrayList.add(DeveloperModel("윤석준", "Android", R.drawable.ysj_profile2))
        arrayList.add(DeveloperModel("유재민", "Android", R.drawable.yjm_profile2))
        arrayList.add(DeveloperModel("이현욱", "iOS", R.drawable.lhu_profile))
        arrayList.add(DeveloperModel("김도현", "iOS", R.drawable.kdh_profile))
        arrayList.add(DeveloperModel("강신희", "Design", R.drawable.ksh_profile))
        arrayList.add(DeveloperModel("용석현", "Design", R.drawable.ysh_profile))

        return arrayList
    }
}