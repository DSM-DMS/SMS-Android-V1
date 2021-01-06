package com.dms.sms.feature.timetable.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Result
import com.dms.domain.timetable.response.TimeTableResponse
import com.dms.domain.timetable.usecase.TimeTableUseCase
import com.dms.domain.util.getCurDay
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.timetable.model.TimeTableDateModel
import com.dms.sms.feature.timetable.model.toDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class TimeTableViewModel(private val timeTableUseCase: TimeTableUseCase) : BaseViewModel() {
    val timeTableList = MutableLiveData<ArrayList<String>>().apply{
        value = arrayListOf()
    }

    private var cnt = 1
    private val resultList = arrayListOf<String>()

    private val mondayList = arrayListOf<String>()
    private val tuesdayList = arrayListOf<String>()
    private val wednesdayList = arrayListOf<String>()
    private val thursdayList = arrayListOf<String>()
    private val fridayList= arrayListOf<String>()


    init {
        getTimeTable()
    }

    private fun getTimeTable(){

        for(i in 1 until 6){
            val year = getCurDay(i).substring(0,4)
            val month = getCurDay(i).substring(4,6)
            val day = getCurDay(i).substring(6,8)

            val timeTableDate = TimeTableDateModel(year, month, day)

            timeTableUseCase.execute(timeTableDate.toDomain() , object : DisposableSingleObserver<Result<TimeTableResponse>>(){
                override fun onSuccess(result: Result<TimeTableResponse>) {
                    when(result){
                        is Result.Success -> saveTimeTable(result.value, i)
                        is Result.Failure -> Log.e("getTimeTable Fail", result.reason.toString())
                    }
                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = e.message
                }

            }, AndroidSchedulers.mainThread())
        }
    }

    private fun getTimeTableList() {
        resultList.apply {
            addAll(mondayList)
            addAll(tuesdayList)
            addAll(wednesdayList)
            addAll(thursdayList)
            addAll(fridayList)
        }

        this.timeTableList.value = resultList
    }

    private fun saveTimeTable(timeTableList: TimeTableResponse, i: Int){

        when(i){
            1 -> mondayList.addItem(timeTableList)
            2 -> tuesdayList.addItem(timeTableList)
            3 -> wednesdayList.addItem(timeTableList)
            4 -> thursdayList.addItem(timeTableList)
            5 -> fridayList.addItem(timeTableList)
        }

        if(cnt == 5){
            getTimeTableList()
        }

        cnt ++
    }

    private fun ArrayList<String>.addItem(timeTableList: TimeTableResponse) {
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
}