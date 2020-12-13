package com.dms.sms.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Result
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.domain.auth.dto.LoginResponseData
import com.dms.sms.login.model.LoginModel
import com.dms.sms.login.model.toDomain
import com.dms.sms.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import com.dms.domain.base.Error

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val loginSuccessEvent: LiveData<Boolean> get() = _loginSuccessEvent
    val isAutoLoginChecked = MutableLiveData<Boolean>().apply {
        value = false
    }


    private val _loginSuccessEvent = MutableLiveData<Boolean>()

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
            object : DisposableSingleObserver<Result<LoginResponseData>>() {
                override fun onSuccess(result: Result<LoginResponseData>) {
                    when(result){
                        is Result.Success ->{
                            createToastEvent.value = "로그인에 성공하셨습니다"
                            _loginSuccessEvent.value = true
                            if(isAutoLoginChecked.value!!){
                                saveLoginData()
                            }
                        }
                        is Result.Failure->{
                            when(result.reason){
                                Error.Conflict ->
                                    createToastEvent.value = "존재하지 않는 학생입니다."
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

                }

                override fun onError(e: Throwable) {
                    createToastEvent.value = "로그인 실패"
                    e.printStackTrace()
                }

            },
            AndroidSchedulers.mainThread()
        )


    }
    private fun saveLoginData(){

    }

    private fun setAutoLogin(){

    }

    private fun loginSuccess() {

    }

    private fun loginFailed() {

    }



}