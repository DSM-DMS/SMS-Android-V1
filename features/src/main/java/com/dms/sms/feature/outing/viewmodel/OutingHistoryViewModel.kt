package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.usecase.GetOutingListUseCase
import com.dms.domain.outing.usecase.GetStudentUUIDUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.outing.model.OutingModel
import com.dms.sms.feature.outing.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class OutingHistoryViewModel(
    private val outingListUseCase: GetOutingListUseCase,
    private val getStudentUUIDUseCase: GetStudentUUIDUseCase
) : BaseViewModel() {
    val outingHistoryList = MutableLiveData<ArrayList<OutingModel>>().apply {
        value = ArrayList(emptyList())
    }

    init {
        getStudentUUID()
    }

    private fun getStudentUUID() {
        getStudentUUIDUseCase.execute(Unit, object : DisposableSingleObserver<Result<String>>() {
            override fun onSuccess(result: Result<String>) {
                when (result) {
                    is Result.Success -> getOutingHistoryList(result.value)
                    is Result.Failure -> createToastEvent.value = "실패"

                }
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        }, AndroidSchedulers.mainThread())
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
                    createToastEvent.value = e.message
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun failGetOutingList(result: Result.Failure<OutingListResponse>) {
        when (result.reason) {
            Error.Conflict ->
                createToastEvent.value = "이미 해당날짜에 대기 중인 외출증이 있습니다. "
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createToastEvent.value = "외출 시간을 다시 확인해주세요"
            Error.UnAuthorized ->
                createToastEvent.value = "외출증 생성 실패"
            Error.Forbidden ->
                createToastEvent.value = "외출증 생성 실패"
            Error.NotFound ->
                createToastEvent.value = "외출증 생성 실패"
            Error.Timeout ->
                createToastEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown ->
                createToastEvent.value = "알 수 없는 오류 발생"
        }
    }

    private fun getOutingList(outingList: List<OutingModel>) {
        this.outingHistoryList.value = ArrayList(outingList)
    }

    fun clickBack() {
        backEvent.call()
    }
}