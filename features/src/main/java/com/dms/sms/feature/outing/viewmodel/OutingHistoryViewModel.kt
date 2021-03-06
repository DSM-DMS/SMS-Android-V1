package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.usecase.GetOutingListUseCase
import com.dms.domain.outing.usecase.GetStudentUUIDUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.outing.model.OutingModel
import com.dms.sms.feature.outing.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class OutingHistoryViewModel(
    private val outingListUseCase: GetOutingListUseCase,
    private val getStudentUUIDUseCase: GetStudentUUIDUseCase
) : BaseViewModel() {
    private lateinit var studentUUID: String

    val clickOutingApplyEvent = SingleLiveEvent<Unit>()

    val historyResult = MutableLiveData(true)
    val outingHistoryList = MutableLiveData<ArrayList<OutingModel>>().apply {
        value = ArrayList(emptyList())
    }

    init {
        getStudentUUID()
    }

    private fun getStudentUUID() {
        val thread = Thread {
            studentUUID = getStudentUUIDUseCase.getUUID("")
        }
        thread.start()
        thread.join()

        getOutingHistoryList(studentUUID)
    }

    private fun getOutingHistoryList(studentUUID: String) {
        outingListUseCase.execute(
            studentUUID, object : DisposableSingleObserver<Result<OutingListResponse>>() {
                override fun onSuccess(result: Result<OutingListResponse>) {
                    when (result) {
                        is Result.Success -> getOutingList(result.value.outing.map { it.toModel() })
                        is Result.Failure -> failGetOutingList(result)
                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = e.message
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun failGetOutingList(result: Result.Failure<OutingListResponse>) {
        when (result.reason) {
            Error.Conflict ->
                createSnackEvent.value = "이미 해당날짜에 대기 중인 외출증이 있습니다. "
            Error.InternalServer ->
                createSnackEvent.value = "서버 오류 발생"
            Error.Network ->
                createSnackEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createSnackEvent.value = "외출 시간을 다시 확인해주세요"
            Error.UnAuthorized ->
                createSnackEvent.value = "외출증 생성 실패"
            Error.Forbidden ->
                createSnackEvent.value = "외출증 생성 실패"
            Error.NotFound ->
                createSnackEvent.value = "외출증 생성 실패"
            Error.Timeout ->
                createSnackEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown ->
                createSnackEvent.value = "알 수 없는 오류 발생"
        }
    }

    private fun getOutingList(outingList: List<OutingModel>) {
        this.outingHistoryList.value = ArrayList(outingList)
    }

    fun clickBack() {
        backEvent.call()
    }

    fun clickOutingApply() {
        return clickOutingApplyEvent.call()
    }
}