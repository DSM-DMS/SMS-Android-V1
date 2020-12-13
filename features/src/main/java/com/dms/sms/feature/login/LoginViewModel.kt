package com.dms.sms.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Result
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.domain.auth.dto.LoginResponseData
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.login.model.LoginModel
import com.dms.sms.feature.login.model.toDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val loginSuccessEvent: LiveData<Boolean> get() = _loginSuccessEvent


    private val _loginSuccessEvent = MutableLiveData<Boolean>()

    private val _isAllLoginInfoFilled = MediatorLiveData<Boolean>().apply {
        addSource(idText) {
            value = it.isNotBlank() && !it.isNullOrEmpty()
        }
        addSource(passwordText) {
            value = it.isNotBlank() && !it.isNullOrEmpty()
        }
    }


    val isAllLoginInfoFilled: LiveData<Boolean> get() = _isAllLoginInfoFilled

    fun onLoginClicked() {
        _loginSuccessEvent.value = true

        loginUseCase.execute(
            LoginModel(idText.value!!, passwordText.value!!).toDomain(),
            object : DisposableSingleObserver<Result<LoginResponseData>>() {
                override fun onSuccess(result: Result<LoginResponseData>) {
                    when(result){
                        is Result.Success ->{
                            _loginSuccessEvent.value = true

                        }
                        is Result.Failure->{

                        }
                    }

                }

                override fun onError(e: Throwable) {

                }

            },
            AndroidSchedulers.mainThread()
        )


    }

    private fun loginSuccess() {

    }

    private fun loginFailed() {

    }


}