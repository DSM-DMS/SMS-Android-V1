package com.dms.sms.feature.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.domain.auth.usecase.SaveLoginDataUseCase
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.login.model.LoggedInUserModel
import com.dms.sms.feature.login.model.LoginModel
import com.dms.sms.feature.login.model.toDomain
import com.dms.sms.feature.login.model.toEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class LoginViewModel(private val loginUseCase: LoginUseCase, private val saveLoginDataUseCase: SaveLoginDataUseCase) : BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val loginSuccessEvent: LiveData<Boolean> get() = _loginSuccessEvent
    val loginErrorEvent: LiveData<Boolean> get() = _loginErrorEvent
    val isAutoLoginChecked = MutableLiveData<Boolean>().apply {
        value = false
    }


    private val _loginSuccessEvent = MutableLiveData<Boolean>()
    private val _loginErrorEvent = MutableLiveData<Boolean>()

    private val _isAllLoginInfoFilled = MediatorLiveData<Boolean>().apply {
        addSource(idText) {
            value = !it.isNullOrBlank() && !passwordText.value.isNullOrBlank()
        }
        addSource(passwordText) {
            value = !it.isNullOrBlank() && !idText.value.isNullOrBlank()
        }
    }


    val isAllLoginInfoFilled: LiveData<Boolean> get() = _isAllLoginInfoFilled

    fun onLoginClicked() {

        loginUseCase.execute(
            LoginModel(idText.value!!, passwordText.value!!).toDomain(),
            object : DisposableSingleObserver<Result<LoginResponse>>() {
                override fun onSuccess(result: Result<LoginResponse>) {
                    when(result){
                        is Result.Success ->{
                            loginSuccess(result)

                        }
                        is Result.Failure->{
                            loginFailed(result)
                        }
                    }

                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "로그인 실패"
                    e.printStackTrace()
                }

            },
            AndroidSchedulers.mainThread()
        )


    }
    private fun saveLoginData(loginResponse: LoginResponse){
        saveLoginDataUseCase.execute(LoggedInUserModel(loginResponse.accessToken,loginResponse.studentUUID,
            isAutoLoginChecked.value!!
        ).toEntity(), object : DisposableSingleObserver<Result<Unit>>(){
            override fun onSuccess(result : Result<Unit>) {
                when(result){
                    is Result.Success-> {
                        Log.d("자동 로그인","성공")
                    }
                    is Result.Failure->{
                        createToastEvent.value = "자동 로그인 설정이 실패했습니다."
                    }
                }
            }

            override fun onError(e: Throwable) {
                createToastEvent.value = "자동 로그인 설정이 실패했습니다."
            }
        },AndroidSchedulers.mainThread())
    }


    private fun loginSuccess(result: Result.Success<LoginResponse>) {
        createToastEvent.value = "로그인에 성공하셨습니다"
        _loginSuccessEvent.value = true
        saveLoginData(result.value)


    }

    private fun loginFailed(result: Result.Failure<LoginResponse>) {
        when(result.reason){
            Error.Conflict ->
                _loginErrorEvent.value = true
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createToastEvent.value = "로그인 실패"
            Error.UnAuthorized ->
                createToastEvent.value = "로그인 실패"
            Error.Forbidden ->
                createToastEvent.value = "로그인 실패"
            Error.NotFound ->
                createToastEvent.value = "로그인 실패"
            Error.Timeout ->
                createToastEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown ->
                createToastEvent.value = "알 수 없는 오류 발생"

        }

    }



}