package com.dms.sms.feature.timetable.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Result
import com.dms.domain.timetable.response.TimeTableListResponse
import com.dms.domain.timetable.usecase.TimeTableUseCase
import com.dms.domain.util.getCurDay
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.timetable.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class TimeTableViewModel(private val timeTableUseCase: TimeTableUseCase) : BaseViewModel() {
    val mondayTimeTableList = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val tuesdayTimeTableList = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val wednesdayTimeTableList = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val thursdayTimeTableList = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val fridayTimeTableList = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }

    private val mondayList = arrayListOf<String>()
    private val tuesdayList = arrayListOf<String>()
    private val wednesdayList = arrayListOf<String>()
    private val thursdayList = arrayListOf<String>()
    private val fridayList = arrayListOf<String>()

    init {
        getTimeTable()
    }

    private fun getTimeTable() {

        val year = getCurDay(1).substring(0, 4)
        val month = getCurDay(1).substring(4, 6)
        val day = getCurDay(1).substring(6, 8)

        val timeTableDate = TimeTableDateModel(year, month, day)

        timeTableUseCase.execute(
            timeTableDate.toDomain(),
            object : DisposableSingleObserver<Result<TimeTableListResponse>>() {
                override fun onSuccess(result: Result<TimeTableListResponse>) {
                    when (result) {
                        is Result.Success -> saveTimeTable(result.value.toModel())
                        is Result.Failure -> Log.e("getTimeTable Fail", result.reason.toString())
                    }
                }
                override fun onError(e: Throwable) {
                    createSnackEvent.value = e.message
                }
            },AndroidSchedulers.mainThread())
    }


    private fun saveTimeTable(timeTableList: TimeTableListResponseModel) {

        for(i in 1..5){
            when(i) {
                1 -> mondayList.addItem(timeTableList.timeTables[0])
                2 -> tuesdayList.addItem(timeTableList.timeTables[1])
                3 -> wednesdayList.addItem(timeTableList.timeTables[2])
                4 -> thursdayList.addItem(timeTableList.timeTables[3])
                5 -> fridayList.addItem(timeTableList.timeTables[4])
            }
        }

        for(i in 1..5){
            when(i) {
                1 -> checkNoneTimeTable(mondayList)
                2 -> checkNoneTimeTable(tuesdayList)
                3 -> checkNoneTimeTable(wednesdayList)
                4 -> checkNoneTimeTable(thursdayList)
                5 -> checkNoneTimeTable(fridayList)
            }
        }

        mondayTimeTableList.value = mondayList
        tuesdayTimeTableList.value = tuesdayList
        wednesdayTimeTableList.value = wednesdayList
        thursdayTimeTableList.value = thursdayList
        fridayTimeTableList.value = fridayList
    }

    private fun checkNoneTimeTable(list: ArrayList<String>) {
        for(i in 0 until list.size) {
            if(list[i] == ""){
                list[i] = "-"
            }
        }
    }

    private fun ArrayList<String>.addItem(timeTableList: TimeTableResponseModel) {
        this.apply {
            add(timeTableList.time1)
            add(timeTableList.time2)
            add(timeTableList.time3)
            add(timeTableList.time4)
            add(timeTableList.time5)
            add(timeTableList.time6)
            add(timeTableList.time7)
        }
    }

    fun onClickNext() {
//        _currentYear.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).year
//        _currentMonth.value = calculateTime(currentYear.value!!, currentMonth.value!!.plus(1)).month
    }

    fun onClickPrevious() {

    }
}