package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.SearchPlaceListResponse
import com.dms.domain.outing.usecase.GetPlaceListUseCase
import com.dms.domain.outing.usecase.OutingUseCase
import com.dms.domain.util.getCurrentDate
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.outing.model.OutingApplyModel
import com.dms.sms.feature.outing.model.PlaceListModel
import com.dms.sms.feature.outing.model.toDomain
import com.dms.sms.feature.outing.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*

class OutingApplyViewModel(
    private val outingUseCase: OutingUseCase,
    private val getPlaceListUseCase: GetPlaceListUseCase
) : BaseViewModel() {
    val searchPlaceList = MutableLiveData<ArrayList<PlaceListModel>>().apply {
        value = ArrayList(emptyList())
    }

    var applyDate: String? = getCurrentDate()
    var startTime: String? = null
    var endTime: String? = null
    val calendar: Calendar = Calendar.getInstance(Locale.KOREA)
    var inputStartTime: String? = null
    var inputEndTime: String? = null
    private val localDate = getCurrentDate()
    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.KOREA).apply {
        timeZone = TimeZone.getTimeZone("Asia/Seoul")
    }

    val outingWithDisease = MutableLiveData(true)
    val outingDate = MutableLiveData(setToday(localDate))
    val searchPlaceResult = MutableLiveData(false)
    val outingReason = MutableLiveData<String>()
    val outingPlace = MutableLiveData<String>()
    val outingStartTime = MutableLiveData<String>()
    val outingEndTime = MutableLiveData<String>()
    val searchPlaceEt = MutableLiveData<String>("")

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
    val startTimeEvent = SingleLiveEvent<Unit>()
    val endTimeEvent = SingleLiveEvent<Unit>()
    val searchPlaceEvent = SingleLiveEvent<Unit>()
    val searchPlaceItemEvent = SingleLiveEvent<Unit>()

    fun applyOuting() {
        val startTime = (changeToUnixTime(applyDate!!, startTime!!).time / 1000).toString()
        val endTime = (changeToUnixTime(applyDate!!, endTime!!).time / 1000).toString()

        val outingModel = OutingApplyModel(
            Integer.parseInt(startTime),
            Integer.parseInt(endTime),
            outingPlace.value!!,
            outingReason.value!!,
            isDisease()
        )

        outingUseCase.execute(
            outingModel.toDomain(),
            object : DisposableSingleObserver<Result<Unit>>() {
                override fun onSuccess(result: Result<Unit>) {
                    when (result) {
                        is Result.Success -> createOutingSuccess()
                        is Result.Failure -> failOutingSuccess(result)
                    }
                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "외출증 생성에 실패하였습니다. "
                }
            },
            AndroidSchedulers.mainThread()
        )

    }

    private fun createOutingSuccess() {
        createToastEvent.value = "외출증 생성에 성공하셨습니다."
        createOutingSuccessEvent.call()
    }

    private fun failOutingSuccess(result: Result.Failure<Unit>) {
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

    fun searchPlace() {
        if (!searchPlaceEt.value.isNullOrEmpty()) {
            getPlaceListUseCase.execute(
                searchPlaceEt.value!!,
                object : DisposableSingleObserver<Result<SearchPlaceListResponse>>() {
                    override fun onSuccess(result: Result<SearchPlaceListResponse>) {
                        when (result) {
                            is Result.Success -> searchPlaceList.value =
                                ArrayList(result.value.searchPlace.map { it.toModel() })
                            is Result.Failure -> failGetPlace(result)
                        }
                    }

                    override fun onError(e: Throwable) {

                    }
                },
                AndroidSchedulers.mainThread()
            )
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

    private fun failGetPlace(result: Result.Failure<SearchPlaceListResponse>) {
        when (result.reason) {
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.Unknown ->
                createToastEvent.value = "알 수 없는 오류 발생"
            Error.Locked ->
                createToastEvent.value = "3초 뒤에 다시 시도해주세요"
            else ->
                createToastEvent.value = "알 수 없는 오류 발생"
        }
    }

    fun setSearchPlace(position: Int) {
        searchPlaceItemEvent.call()
        outingPlace.value = searchPlaceList.value?.get(position)?.placeAddress
    }

    private fun setToday(localDate: String): String {
        val year = localDate.substring(0, 4)
        val month = localDate.substring(4, 6)
        val day = localDate.substring(6, 8)

        return "${year}년 ${month}월 ${day}일"
    }

    fun compareTime(time: String, type: Int): Int {
        when (type) {
            1 -> {
                val regularStartTime = "16:20:00".split(":")
                val inputTime = time.split(":")

                return if (outingEndTime.value.isNullOrEmpty()) {
                    if ((regularStartTime[0].toInt() * 60 + regularStartTime[1].toInt()) - (inputTime[0].toInt() * 60 + inputTime[1].toInt()) < 0) {
                        1 // 1 - 정규시간이 입력시간보다 작다 즉 옳은 경우
                    } else 2 // 2 - 정규시간이 입력시간보다 크다 즉 틀린 경우
                } else {
                    val inputEndTime = this.inputEndTime!!.split(":")
                    if ((regularStartTime[0].toInt() * 60 + regularStartTime[1].toInt()) - (inputTime[0].toInt() * 60 + inputTime[1].toInt()) < 0) {
                        if (inputEndTime[0].toInt() * 60 + inputEndTime[1].toInt() >= (inputTime[0].toInt() * 60 + inputTime[1].toInt())) {
                            3 // 도착 시간보다 시작 시간이 작다 즉 옳은 경우
                        } else 4 // 도착 시간보다 시작 시간이 크다 즉 틀린 경우
                    } else 2
                }
            }
            2 -> {
                val regularEndTime = "20:30:00".split(":")
                val inputTime = time.split(":")

                return if (outingStartTime.value.isNullOrEmpty()) {
                    return if ((regularEndTime[0].toInt() * 60 + regularEndTime[1].toInt()) - (inputTime[0].toInt() * 60 + inputTime[1].toInt()) >= 0) {
                        1 //정규 종료시간이 입력 종료시간보다 크다 즉 옳은 경우
                    } else 2 // 정규 종료시간이 입력 종류시간보다 작다 즉 틀린 경우
                } else {
                    val inputStartTime = this.inputStartTime!!.split(":")
                    if ((regularEndTime[0].toInt() * 60 + regularEndTime[1].toInt()) - (inputTime[0].toInt() * 60 + inputTime[1].toInt()) >= 0) {
                        if (inputStartTime[0].toInt() * 60 + inputStartTime[1].toInt() <= (inputTime[0].toInt() * 60 + inputTime[1].toInt())) {
                            3 // 도착 시간보다 시작 시간이 크다 즉 옳은 경우
                        } else 4 // 도착 시간보다 시작 시간이 작다 즉 틀린 경우
                    } else 2
                }
            }
        }
        return 0
    }

    private fun checkFullText(): Boolean =
        !outingDate.value.isNullOrBlank() && !outingEndTime.value.isNullOrBlank() && !outingReason.value.isNullOrBlank() && !outingPlace.value.isNullOrBlank() && !outingStartTime.value.isNullOrBlank()

    fun clickDisease() = diseaseEvent.call()
    fun clickStartTime() = startTimeEvent.call()
    fun clickEndTime() = endTimeEvent.call()
    fun clickSuccess() = onSuccessDialogEvent.call()
    fun clickCancel() = onCancelEvent.call()
    fun clickDiseaseCancel() = onDiseaseCancelEvent.call()
    fun clickSearchPlace() = searchPlaceEvent.call()
    fun clickBack() = backEvent.call()
}