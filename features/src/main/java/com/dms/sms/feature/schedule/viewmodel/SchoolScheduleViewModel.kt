package com.dms.sms.feature.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.schedule.entity.Schedules
import com.dms.domain.schedule.usecase.GetScheduleUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.schedule.calculateTime
import com.dms.sms.feature.schedule.getCurrentDate
import com.dms.sms.feature.schedule.getCurrentDay
import com.dms.sms.feature.schedule.getCurrentMonth
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import com.dms.sms.feature.schedule.model.ScheduleModel
import com.dms.sms.feature.schedule.model.toEntity
import com.dms.sms.feature.schedule.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class SchoolScheduleViewModel(private val getScheduleUseCase: GetScheduleUseCase) :
    BaseViewModel() {

    private val _isSelected = MutableLiveData<String?>()
    val isSelected: LiveData<String?> get() = _isSelected

    private val _currentMonth = MutableLiveData<Int>()
    val currentMonth: LiveData<Int> get() = _currentMonth

    private val _currentYear = MutableLiveData<Int>()
    val currentYear: LiveData<Int> get() = _currentYear

    private val _schedule = MutableLiveData<List<ScheduleModel>>()
    val schedule: LiveData<List<ScheduleModel>> get() = _schedule

    val selectedDateSchedule = MutableLiveData<List<ScheduleModel>>()

    val onClickTimeTableSwitch = SingleLiveEvent<Unit>()

    fun onCreate() {
        _currentYear.value = getCurrentDate().year
        _currentMonth.value = getCurrentDate().month
        getSchedule()
    }

    fun onClickDate(schedules: List<ScheduleModel>, selectedDay: String) {
        Log.d("selectedDay", selectedDay)
        _isSelected.value = selectedDay
        selectedDateSchedule.value = schedules
    }

    fun onClickSwitch() {
        onClickTimeTableSwitch.call()
    }

    fun onClickNext() {
        _currentYear.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).year
        _currentMonth.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).month
        selectedDateSchedule.value = listOf()
        _isSelected.postValue(null)
        _schedule.value = listOf()
        getSchedule()

    }

    fun onClickPrevious() {
        _currentYear.value = calculateTime(currentYear.value!!, currentMonth.value!!.minus(1)).year
        _currentMonth.value =
            calculateTime(currentYear.value!!, currentMonth.value!!.minus(1)).month
        selectedDateSchedule.value = listOf()
        _isSelected.postValue(null)
        _schedule.value = listOf()
        getSchedule()

    }

    private fun getSchedule() {
        getScheduleUseCase.execute(
            ScheduleDateModel(
                currentYear.value!!,
                currentMonth.value!!
            ).toEntity(),
            object : DisposableSingleObserver<Result<Schedules>>() {
                override fun onSuccess(result: Result<Schedules>) {
                    when (result) {
                        is Result.Success -> {
                            _schedule.value =
                                result.value.schedules.map { it.toModel() }.sortedBy { it.startDay }
                            if (currentMonth.value!! == getCurrentMonth().toInt())
                                _isSelected.value = getCurrentDay()

                        }
                        is Result.Failure -> {
                            getScheduleFailed(result)
                        }
                    }

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            }, AndroidSchedulers.mainThread()
        )

    }

    private fun getScheduleFailed(result: Result.Failure<Schedules>) {
        when (result.reason) {
            Error.Conflict ->
                createToastEvent.value = "오류 발생"
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createToastEvent.value = "오류 발생"
            Error.UnAuthorized -> {
                expiredTokenEvent.call()
                createToastEvent.value = "로그인 정보가 만료되었습니다, 다시 로그인 해주십시오"
            }
            Error.Forbidden -> {
                createToastEvent.value = "오류 발생"
            }
            Error.NotFound ->
                createToastEvent.value = "오류 발생"
            Error.Timeout ->
                createToastEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown -> createToastEvent.value = "알 수 없는 오류 발생"
            Error.Locked -> createToastEvent.value = "알 수 없는 오류 발생"

        }

    }

}