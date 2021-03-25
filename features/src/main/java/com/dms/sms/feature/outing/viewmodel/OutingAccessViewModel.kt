package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.usecase.GetDetailOutingUseCase
import com.dms.domain.outing.usecase.GetOutingListUseCase
import com.dms.domain.outing.usecase.GetStudentUUIDUseCase
import com.dms.domain.outing.usecase.PostOutingActionUseCase
import com.dms.domain.util.isToday
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.outing.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class OutingAccessViewModel(
    private val getDetailOutingUseCase: GetDetailOutingUseCase,
    private val getOutingListUseCase: GetOutingListUseCase,
    private val getStudentUUIDUseCase: GetStudentUUIDUseCase,
    private val postOutingActionUseCase: PostOutingActionUseCase
) : BaseViewModel() {

    private lateinit var studentUUID: String
    private lateinit var outingUUID: String
    val detailOutingData = MutableLiveData<DetailOutingModel>()
    val accessResult = MutableLiveData(true)
    val outingStartTv = MutableLiveData(true)
    val accessBtnResult = MutableLiveData(false)

    val outingStartDialogEvent = SingleLiveEvent<Unit>()
    val outingStartConfirmEvent = SingleLiveEvent<Unit>()
    val outingStartCancelEvent = SingleLiveEvent<Unit>()

    private fun getDetailOuting(outingUUID: String) {
        getDetailOutingUseCase.execute(
            outingUUID, object : DisposableSingleObserver<Result<DetailOutingResponse>>() {
                override fun onSuccess(result: Result<DetailOutingResponse>) {
                    when (result) {
                        is Result.Success -> successDetailOuting(result)
                        is Result.Failure -> failDetailOuting(result)
                    }
                }

                override fun onError(e: Throwable) {
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    fun getStudentUUID() {
        val thread = Thread {
            studentUUID = getStudentUUIDUseCase.getUUID("")
        }
        thread.start()
        thread.join()

        getOutingList()
    }

    private fun getOutingList() {
        getOutingListUseCase.execute(
            studentUUID, object : DisposableSingleObserver<Result<OutingListResponse>>() {
                override fun onSuccess(result: Result<OutingListResponse>) {
                    when (result) {
                        is Result.Success -> isTodayOuting(result.value.outing.map { it.toModel() })
                        is Result.Failure -> accessResult.value = true
                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = e.message
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun failDetailOuting(result: Result.Failure<DetailOutingResponse>) {
        accessResult.value = true

        when (result.reason) {
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
                createSnackEvent.value = "존재하지 않는 외출증 입니다."
            Error.Timeout ->
                createSnackEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown ->
                createSnackEvent.value = "알 수 없는 오류 발생"

        }
    }

    private fun successDetailOuting(result: Result.Success<DetailOutingResponse>) {
        accessResult.value = false
        detailOutingData.value = result.value.toModel()

        when (result.value.outingStatus) {
            "2" -> {
                accessBtnResult.value = true
                outingStartTv.value = true
            }
            "3" -> {
                accessBtnResult.value = true
                outingStartTv.value = false
            }
            else -> {
                accessBtnResult.value = false
            }
        }
    }

    fun clickStart() {
        if (outingStartTv.value!!) {
            compareTime(detailOutingData.value!!.startTime)
        }
    }

    fun startOrFinishOuting() {
        val accessOutingModel = AccessOutingModel(outingUUID, "start")
        postOutingActionUseCase.execute(
            accessOutingModel.toDomain(),
            object : DisposableSingleObserver<Result<Unit>>() {
                override fun onSuccess(result: Result<Unit>) {
                    when (result) {
                        is Result.Success -> {
                            outingStartTv.value = !outingStartTv.value!!
                        }
                        is Result.Failure -> createSnackEvent.value = result.reason.toString()
                    }
                }

                override fun onError(e: Throwable) {
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun isTodayOuting(outingList: List<OutingModel>) {
        for (i in outingList.indices) {
            if (isToday(outingList[i].startTime.toLong())) {
                outingUUID = outingList[i].outingUUID
                getDetailOuting(outingList[i].outingUUID)
            }
        }
    }

    private fun compareTime(time: String) {
        val nowTime: Long = System.currentTimeMillis() / 1000
        val startTime: Long = time.toLong()

        if (nowTime > startTime) {
            outingStartDialogEvent.call()
        } else {
            createSnackEvent.value = "아직 외출시간이 아닙니다."
        }
    }

    fun clickBack() = backEvent.call()
    fun clickOutingStartConfirm() = outingStartConfirmEvent.call()
    fun clickOutingStartCancel() = outingStartCancelEvent.call()
}