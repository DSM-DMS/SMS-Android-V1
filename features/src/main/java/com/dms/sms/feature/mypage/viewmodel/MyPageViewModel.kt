package com.dms.sms.feature.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.dms.domain.base.Error
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

class MyPageViewModel(private val getUserProfileUseCase: GetUserProfileUseCase, private val getStudentUUIDUseCase: GetStudentUUIDUseCase): BaseViewModel(), LifecycleObserver {
    val userModel = MutableLiveData<UserModel>()

    val changePwEvent = SingleLiveEvent<Unit>()
    val logoutEvent = SingleLiveEvent<Unit>()
    val developerEvent = SingleLiveEvent<Unit>()


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun getStudentUUID(){
        getStudentUUIDUseCase.execute(Unit, object : DisposableSingleObserver<Result<String>>() {
            override fun onSuccess(result: Result<String>) {
                when (result) {
                    is Result.Success -> getUserProfile(result.value)
                    is Result.Failure -> {
                        Log.e("fail","getstudentuuid")
                    }
                }
            }

            override fun onError(e: Throwable) {

            }

        }, AndroidSchedulers.mainThread())
    }

    fun getUserProfile(studentUUID: String){
        getUserProfileUseCase.execute(studentUUID, object: DisposableSingleObserver<Result<UserResponse>>(){
            override fun onSuccess(result: Result<UserResponse>) {
                when(result){
                    is Result.Success -> userModel.value = result.value.toModel()
                    is Result.Failure -> onFailGetUserProfile(result)
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        }, AndroidSchedulers.mainThread())
    }
    fun onFailGetUserProfile(result: Result.Failure<UserResponse>){
        when(result.reason){
            Error.Network -> createToastEvent.value = "실패"
            Error.BadRequest -> createToastEvent.value = "실패"
            Error.UnAuthorized -> {
                expiredTokenEvent.call()
                createToastEvent.value = "로그인 정보가 만료되었습니다. 다시 로그인 해주세요."
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
            Error.Conflict -> createToastEvent.value = "알 수 없는 오류 발생"
            Error.InternalServer -> createToastEvent.value = "서버 오류 발생"
        }

    }

    fun clickChangePw() = changePwEvent.call()
    fun clickLogOut() = logoutEvent.call()
    fun clickDeveloper() = developerEvent.call()
}