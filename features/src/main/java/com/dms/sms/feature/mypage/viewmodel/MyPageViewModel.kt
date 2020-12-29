package com.dms.sms.feature.mypage.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Result
import com.dms.domain.mypage.response.UserResponse
import com.dms.domain.mypage.usecase.GetStudentUUIDUseCase
import com.dms.domain.mypage.usecase.GetUserProfileUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.mypage.model.UserModel
import com.dms.sms.feature.mypage.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class MyPageViewModel(private val getUserProfileUseCase: GetUserProfileUseCase, private val getStudentUUIDUseCase: GetStudentUUIDUseCase): BaseViewModel() {
    val userModel = MutableLiveData<UserModel>()

    val changePwEvent = SingleLiveEvent<Unit>()
    val logoutEvent = SingleLiveEvent<Unit>()
    val developerEvent = SingleLiveEvent<Unit>()

    init {
        getStudentUUID()
    }

    private fun getStudentUUID(){
        getStudentUUIDUseCase.execute(Unit, object: DisposableSingleObserver<Result<String>>(){
            override fun onSuccess(result: Result<String>) {
                when(result){
                    is Result.Success -> getUserProfile(result.value)
                    is Result.Failure -> createToastEvent.value = "실패"
                }
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

        },AndroidSchedulers.mainThread())
    }

    fun getUserProfile(studentUUID: String){
        getUserProfileUseCase.execute(studentUUID, object: DisposableSingleObserver<Result<UserResponse>>(){
            override fun onSuccess(result: Result<UserResponse>) {
                when(result){
                    is Result.Success -> userModel.value = result.value.toModel()
                    is Result.Failure -> createToastEvent.value = "실패"
                }
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

        }, AndroidSchedulers.mainThread())
    }

    fun clickChangePw() = changePwEvent.call()
    fun clickLogOut() = logoutEvent.call()
    fun clickDeveloper() = developerEvent.call()
}