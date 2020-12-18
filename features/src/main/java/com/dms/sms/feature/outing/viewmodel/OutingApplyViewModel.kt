package com.dms.sms.feature.outing.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.OutingResponse
import com.dms.domain.outing.usecase.OutingUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.outing.model.OutingModel
import com.dms.sms.feature.outing.model.toDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*

class OutingApplyViewModel(private val outingUseCase: OutingUseCase) : BaseViewModel() {

    var applyDate: String? = null
    var startTime: String? = null
    var endTime: String? = null
    val calendar: Calendar = Calendar.getInstance(Locale.KOREA)
    private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    val outingWithDisease = MutableLiveData(true)
    val outingDate = MutableLiveData<String>()
    val outingReason = MutableLiveData<String>()
    val outingPlace = MutableLiveData<String>()
    val outingStartTime = MutableLiveData<String>()
    val outingEndTime = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(outingDate) { value = checkFullText() }
        addSource(outingReason) { value = checkFullText() }
        addSource(outingPlace) { value = checkFullText() }
        addSource(outingStartTime) { value = checkFullText() }
        addSource(outingEndTime) { value = checkFullText() }
    }

    val createOutingSuccessEvent = SingleLiveEvent<Unit>()
    val onDiseaseCancelEvent = SingleLiveEvent<Unit>()
    val onCancelEvent = SingleLiveEvent<Unit>()
    val onSuccessDialogEvent = SingleLiveEvent<Unit>()
    val diseaseEvent = SingleLiveEvent<Unit>()
    val dateEvent = SingleLiveEvent<Unit>()
    val startTimeEvent = SingleLiveEvent<Unit>()
    val endTimeEvent = SingleLiveEvent<Unit>()

    fun applyOuting() {
        val startTime = (changeToUnixTime(applyDate!!, startTime!!).time / 1000).toString()
        val endTime = (changeToUnixTime(applyDate!!, endTime!!).time / 1000).toString()

        val outingModel = OutingModel(Integer.parseInt(startTime), Integer.parseInt(endTime), outingPlace.value!!, outingReason.value!!, isDisease())

        outingUseCase.execute(outingModel.toDomain(), object: DisposableSingleObserver<Result<Unit>>(){
            override fun onSuccess(result: Result<Unit>) {
                when(result){
                    is Result.Success -> createOutingSuccess(result)
                    is Result.Failure -> failOutingSuccess(result)
                }
            }
            override fun onError(e: Throwable) {
                createToastEvent.value = "외출증 생성에 실패하였습니다. "
            }
        },AndroidSchedulers.mainThread())
    }

    private fun createOutingSuccess(result: Result.Success<Unit>){
        createToastEvent.value = "외출증 생성에 성공하셨습니다."
        createOutingSuccessEvent.call()
    }

    private fun failOutingSuccess(result: Result.Failure<Unit>){
        when(result.reason){
            Error.Conflict ->
                createToastEvent.value = "이미 해당날짜에 대기 중인 외출증이 있습니다. "
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createToastEvent.value = "외출증 생성 실패"
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

    private fun changeToUnixTime(applyDate: String, time: String): Date =
        simpleDateFormat.parse("$applyDate $time")!!

    private fun isDisease(): String {
        return if (outingWithDisease.value!!) {
            "normal"
        } else {
            "emergency"
        }
    }

    private fun checkFullText(): Boolean =
        !outingDate.value.isNullOrBlank() && !outingEndTime.value.isNullOrBlank() && !outingReason.value.isNullOrBlank() && !outingPlace.value.isNullOrBlank() && !outingStartTime.value.isNullOrBlank()


    fun clickDisease() = diseaseEvent.call()
    fun clickDate() = dateEvent.call()
    fun clickStartTime() = startTimeEvent.call()
    fun clickEndTime() = endTimeEvent.call()
    fun clickSuccess() = onSuccessDialogEvent.call()
    fun clickCancel() = onCancelEvent.call()
    fun clickDiseaseCancel() = onDiseaseCancelEvent.call()
}