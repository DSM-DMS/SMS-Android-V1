package com.dms.sms.feature.announcement.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.usecase.GetAnnouncementUseCase
import com.dms.domain.announcement.usecase.GetAnnouncementsUseCase
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
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel

class AnnouncementDetailViewModel(private val getAnnouncementUseCase: GetAnnouncementUseCase,private val getAnnouncementsUseCase: GetAnnouncementsUseCase) :
    BaseViewModel(),
    LifecycleObserver {

    private val _announcementContent = MutableLiveData<List<EJBlock>>()
    val announcementContent: LiveData<List<EJBlock>> get() = _announcementContent

    private val _announcement = MutableLiveData<AnnouncementModel>()
    val announcement: LiveData<AnnouncementModel> get() = _announcement
    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _announcements = MutableLiveData<List<SimpleAnnouncementModel>>()
    val announcements: LiveData<List<SimpleAnnouncementModel>> get() = _announcements

    val backButtonEvent = SingleLiveEvent<Unit>()

    fun onCreate(announcementUUID: String, page : Int) {
        _currentPage.value = page
        Log.d("ddddd",_currentPage.value.toString())
        getAnnouncement(announcementUUID)
    }

    fun onBackButtonClicked() {
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
                            getAnnouncements(currentPage.value!!)
                        }
                        is Result.Failure -> {
                            onFailedToLoadAnnouncement(result)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = "오류 발생"
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun getAnnouncements(announcementsPage: Int) {
        getAnnouncementsUseCase.execute(
            announcementsPage, object : DisposableSingleObserver<Result<Announcements>>() {
                override fun onSuccess(result: Result<Announcements>) {
                    when (result) {
                        is Result.Success -> {
                            _announcements.value =
                                result.value.simpleAnnouncements.map { it.toModel() }
                            Log.d("dddddddd",_announcements.value.toString())

                        }
                        is Result.Failure -> {
                            createSnackEvent.value = "오류 발생"
                        }

                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = "오류 발생"
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    fun onFailedToLoadAnnouncement(result: Result.Failure<Announcement>) {
        when (result.reason) {
            Error.Network -> createSnackEvent.value = "네트워크 오류 발생"
            Error.BadRequest -> createSnackEvent.value = "오류 발생"
            Error.UnAuthorized -> createSnackEvent.value = "인증되지 않은 사용자입니다"
            Error.Forbidden -> createSnackEvent.value = "오류 발생"
            Error.NotFound -> createSnackEvent.value = "글이 존재하지 않습니다"
            Error.Timeout -> createSnackEvent.value = "요청 시간이 너무 오래 걸립니다"
            Error.Conflict -> createSnackEvent.value = "오류 발생"
            Error.InternalServer -> createSnackEvent.value = "서버 오류 발생"
            Error.Unknown -> createSnackEvent.value = "알 수 없는 오류 발생"
        }
    }


}