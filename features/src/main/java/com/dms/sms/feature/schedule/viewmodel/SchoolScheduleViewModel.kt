package com.dms.sms.feature.schedule.viewmodel

import androidx.lifecycle.*
import com.dms.domain.base.Result
import com.dms.domain.schedule.entity.Schedules
import com.dms.domain.schedule.usecase.GetScheduleUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.schedule.calculateTime
import com.dms.sms.feature.schedule.getCurrentDate
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import com.dms.sms.feature.schedule.model.ScheduleModel
import com.dms.sms.feature.schedule.model.toEntity
import com.dms.sms.feature.schedule.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class SchoolScheduleViewModel(private val getScheduleUseCase: GetScheduleUseCase) : BaseViewModel(), LifecycleObserver{

    private val _isSelected = MutableLiveData<String?>()
    val isSelected : LiveData<String?> get() =_isSelected

    private val _currentMonth = MutableLiveData<Int>()
    val currentMonth : LiveData<Int> get() = _currentMonth

    private val _currentYear = MutableLiveData<Int>()
    val currentYear : LiveData<Int> get() =_currentYear

    private val _schedule = MutableLiveData<List<ScheduleModel>>()
    val schedule : LiveData<List<ScheduleModel>> get() =_schedule

    private val _selectedDateSchedule = MutableLiveData<List<ScheduleModel>>()
    val selectedDateSchedule : LiveData<List<ScheduleModel>> get() =_selectedDateSchedule

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        _currentYear.value = getCurrentDate().year
        _currentMonth.value = getCurrentDate().month
        getSchedule()
    }

    fun onClickDate(schedules : List<ScheduleModel>, selectedDay : String) {
        _isSelected.value = selectedDay
        _selectedDateSchedule.value = schedules
    }

    fun onClickNext(){
        _currentYear.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).year
        _currentMonth.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).month
        _selectedDateSchedule.value = listOf()
        _isSelected.value=null
        _schedule.value = listOf()
        getSchedule()

    }
    fun onClickPrevious(){
        _currentYear.value = calculateTime(currentYear.value!!, currentMonth.value!!.minus(1)).year
        _currentMonth.value = calculateTime(currentYear.value!!, currentMonth.value!!.minus(1)).month
        _selectedDateSchedule.value = listOf()
        _isSelected.value=null
        _schedule.value = listOf()
        getSchedule()

    }
    private fun getSchedule(){
        getScheduleUseCase.execute(ScheduleDateModel(currentYear.value!!, currentMonth.value!!).toEntity(),
            object : DisposableSingleObserver<Result<Schedules>>(){
            override fun onSuccess(result: Result<Schedules>) {
                when(result){
                    is Result.Success->{
                        _schedule.value = result.value.schedules.map { it.toModel() }.sortedBy { it.startDate }
                    }
                    is Result.Failure->{

                    }
                }

            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        }, AndroidSchedulers.mainThread())

    }

}