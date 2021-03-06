package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.OutingResponse
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
    val searchPlaceEt = MutableLiveData("")
    val completeMessage = MutableLiveData<String>()

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
    val outingApplyNoticeEvent = SingleLiveEvent<Unit>()
    val outingApplyNoticeConfirmEvent = SingleLiveEvent<Unit>()
    val outingApplyNoticeCancelEvent = SingleLiveEvent<Unit>()
    val outingCompleteEvent = SingleLiveEvent<Unit>()

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
            object : DisposableSingleObserver<Result<OutingResponse>>() {
                override fun onSuccess(result: Result<OutingResponse>) {
                    when (result) {
                        is Result.Success -> {
                            createOutingSuccess()
                            setCompleteMessage(result.value.code)
                        }
                        is Result.Failure -> failOutingSuccess(result)
                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = "외출증 생성에 실패하였습니다. "
                }
            },
            AndroidSchedulers.mainThread()
        )

    }

    private fun createOutingSuccess() {
        createSnackEvent.value = "외출증 생성에 성공하셨습니다."
        createOutingSuccessEvent.call()
    }

    private fun setCompleteMessage(code: Int) {
        when (code) {
            0 -> {
                completeMessage.value = "외출증 신청이 완료되었습니다.\n 승인을 받은 후 모바일을 통해 외출을 시작해주세요."
            }
            -1 -> {
                completeMessage.value = "연결된 학부모 계정이 존재하지 않습니다.\n 선생님께 바로 찾아가 승인을 받아주세요."
            }
            -2 -> {
                completeMessage.value = "학부모가 문자 사용을 동의하지 않았습니다.\n 선생님께 바로 찾아가 승인을 받아주세요."
            }
        }
    }

    private fun failOutingSuccess(result: Result.Failure<OutingResponse>) {
        when (result.reason) {
            Error.Conflict ->
                createSnackEvent.value = "이미 해당날짜에 대기 중인 외출증이 있습니다. "
            Error.InternalServer ->
                createSnackEvent.value = "서버 오류 발생"
            Error.Network ->
                createSnackEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createSnackEvent.value = "외출 시간을 다시 확인해주세요 \n외출 시작 시간이 현재 시간과 같거나 느립니다."
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

    private fun searchPlace(searchPlaceQuery: String) {
        searchPlaceResult.value = true

        if (searchPlaceQuery.isNotEmpty()) {
            getPlaceListUseCase.execute(
                searchPlaceQuery,
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

    fun compareTime(time: String, type: Int): Boolean {
        when (type) {
            1 -> {
                val inputTime = time.split(":")

                return if (outingEndTime.value.isNullOrEmpty()) {
                    true
                } else {
                    val inputEndTime = this.inputEndTime!!.split(":")
                    inputEndTime[0].toInt() * 60 + inputEndTime[1].toInt() >= (inputTime[0].toInt() * 60 + inputTime[1].toInt()) // 도착 시간보다 시작 시간이 크다 즉 틀린 경우\
                }
            }
            2 -> {
                val inputTime = time.split(":")

                return if (outingStartTime.value.isNullOrEmpty()) {
                    true
                } else {
                    val inputStartTime = this.inputStartTime!!.split(":")
                    inputStartTime[0].toInt() * 60 + inputStartTime[1].toInt() <= (inputTime[0].toInt() * 60 + inputTime[1].toInt()) // 도착 시간보다 시작 시간이 작다 즉 틀린 경우
                }
            }
        }
        return false
    }

    fun onSearchPressed(searchQuery: String) {
        if (searchQuery.isNotEmpty() && searchQuery.isNotBlank()) {
            searchPlace(searchQuery)
        } else
            createToastEvent.value = "글을 입력해주세요"
    }

    private fun checkFullText(): Boolean =
        !outingDate.value.isNullOrBlank() && !outingEndTime.value.isNullOrBlank() && !outingReason.value.isNullOrBlank() && !outingPlace.value.isNullOrBlank() && !outingStartTime.value.isNullOrBlank()

    fun clickOutingApplyNoticeConfirm() = outingApplyNoticeConfirmEvent.call()
    fun clickOutingApplyNoticeCancel() = outingApplyNoticeCancelEvent.call()
    fun clickOutingApply() = outingApplyNoticeEvent.call()
    fun clickDisease() = diseaseEvent.call()
    fun clickStartTime() = startTimeEvent.call()
    fun clickEndTime() = endTimeEvent.call()
    fun clickSuccess() = onSuccessDialogEvent.call()
    fun clickCancel() = onCancelEvent.call()
    fun clickDiseaseCancel() = onDiseaseCancelEvent.call()
    fun clickSearchPlace() = searchPlaceEvent.call()
    fun clickComplete() = outingCompleteEvent.call()
    fun clickBack() = backEvent.call()
}