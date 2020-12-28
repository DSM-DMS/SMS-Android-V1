package com.dms.sms.feature.announcement.viewmodel

import androidx.lifecycle.*
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.usecase.GetAnnouncementsUseCase
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel
import com.dms.sms.feature.announcement.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class AnnouncementsViewModel(private val getAnnouncementsUseCase: GetAnnouncementsUseCase) : BaseViewModel(), LifecycleObserver {



    private val _announcements = MutableLiveData<List<SimpleAnnouncementModel>>()
    val announcements : LiveData<List<SimpleAnnouncementModel>> get() = _announcements

    private val _announcementsPages = MutableLiveData<Int>()
    val announcementsPages : LiveData<Int> get() = _announcementsPages

    val announcementEvent = SingleLiveEvent<String>()



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onCreate(){
        getAnnouncements(0)
    }
    fun onClickAnnouncement(annoucementUUID: String){
        announcementEvent.value = annoucementUUID
    }


    private fun getAnnouncements(announcementsPage : Int){
        getAnnouncementsUseCase.execute(announcementsPage, object : DisposableSingleObserver<Result<Announcements>>(){
            override fun onSuccess(result: Result<Announcements>) {
                when(result){
                    is Result.Success-> {
                        _announcements.value = result.value.simpleAnnouncements.map { it.toModel() }
                        _announcementsPages.value = result.value.size
                    }
                    is Result.Failure->{
                        createToastEvent.value = "응 안돼"

                    }

                }
            }

            override fun onError(e: Throwable) {
                createToastEvent.value = "응 안돼"
            }
        },
            AndroidSchedulers.mainThread())
    }





}