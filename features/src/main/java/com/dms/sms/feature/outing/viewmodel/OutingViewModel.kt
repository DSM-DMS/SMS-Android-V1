package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.usecase.GetAnnouncementsUseCase
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.mypage.usecase.GetStudentUUIDUseCase
import com.dms.domain.schedule.usecase.GetScheduleUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class OutingViewModel(private val getAnnouncementsUseCase: GetAnnouncementsUseCase) : BaseViewModel() {

    val applyOutingEvent = SingleLiveEvent<Unit>()
    val outingHistoryEvent = SingleLiveEvent<Unit>()
    val accessOutingEvent = SingleLiveEvent<Unit>()
    val noticeEvent = SingleLiveEvent<Unit>()

    fun onCreate(){
        getAnnouncementsUseCase.execute(1, object : DisposableSingleObserver<Result<Announcements>>(){
            override fun onSuccess(result: Result<Announcements>) {
                when(result){
                    is Result.Success->{}
                    is Result.Failure->{
                        onGetFailed(result)

                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        },
        AndroidSchedulers.mainThread())
    }
    private fun onGetFailed(result : Result.Failure<Announcements>){
        when(result.reason){
            Error.Network -> createSnackEvent.value = "실패"
            Error.BadRequest -> createSnackEvent.value = "실패"
            Error.UnAuthorized -> {
                createSnackEvent.value = "로그인 정보가 만료되었습니다. 다시 로그인 해주세요."

            }
            Error.Forbidden -> {
                expiredTokenEvent.call()
                createSnackEvent.value = "로그인 정보가 만료되었습니다, 다시 로그인 해주십시오"
            }
            Error.NotFound ->
                createSnackEvent.value = "오류 발생"
            Error.Timeout ->
                createSnackEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown -> createSnackEvent.value = "알 수 없는 오류 발생"
            Error.Locked -> createSnackEvent.value = "알 수 없는 오류 발생"
            Error.Conflict -> createSnackEvent.value = "알 수 없는 오류 발생"
            Error.InternalServer -> createSnackEvent.value = "서버 오류 발생"
        }

    }

    fun clickApplyOuting() = applyOutingEvent.call()
    fun clickOutingHistory() = outingHistoryEvent.call()
    fun clickAccessOuting() = accessOutingEvent.call()
    fun clickNoticeEvent() = noticeEvent.call()

}