package com.dms.sms.feature.announcement.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.usecase.GetAnnouncementUseCase
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.announcement.convertToEditorjs
import com.dms.sms.feature.announcement.model.AnnouncementModel
import com.dms.sms.feature.announcement.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import work.upstarts.editorjskit.models.EJBlock
import com.dms.domain.base.Error

class AnnouncementDetailViewModel(private val getAnnouncementUseCase: GetAnnouncementUseCase) :
    BaseViewModel(),
    LifecycleObserver {

    private val _announcementContent = MutableLiveData<List<EJBlock>>()
    val announcementContent: LiveData<List<EJBlock>> get() = _announcementContent

    private val _announcement = MutableLiveData<AnnouncementModel>()
    val announcement: LiveData<AnnouncementModel> get() = _announcement

    val backButtonEvent = SingleLiveEvent<Unit>()

    fun onCreate(announcementUUID: String){
        getAnnouncement(announcementUUID)
    }

    fun onBackButtonClicked(){
        backButtonEvent.call()
    }

    fun getAnnouncement(announcementUUID: String) {

        getAnnouncementUseCase.execute(
            announcementUUID,
            object : DisposableSingleObserver<Result<Announcement>>() {
                override fun onSuccess(result: Result<Announcement>) {
                    when (result) {
                        is Result.Success -> {
                            _announcement.value = result.value.toModel()
                            _announcementContent.value = result.value.content.convertToEditorjs()

                        }
                        is Result.Failure -> {
                            onFailedToLoadAnnouncement(result)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "오류 발생"
                }
            },
            AndroidSchedulers.mainThread()
        )
    }
    fun onFailedToLoadAnnouncement(result: Result.Failure<Announcement>){
        when(result.reason){
            Error.Network -> createToastEvent.value ="네트워크 오류 발생"
            Error.BadRequest -> createToastEvent.value ="오류 발생"
            Error.UnAuthorized -> createToastEvent.value ="인증되지 않은 사용자입니다"
            Error.Forbidden -> createToastEvent.value ="오류 발생"
            Error.NotFound -> createToastEvent.value = "글이 존재하지 않습니다"
            Error.Timeout -> createToastEvent.value = "요청 시간이 너무 오래 걸립니다"
            Error.Conflict -> createToastEvent.value = "오류 발생"
            Error.InternalServer -> createToastEvent.value = "서버 오류 발생"
            Error.Unknown -> createToastEvent.value = "알 수 없는 오류 발생"
        }    }


}