package com.dms.sms.feature.mypage.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.mypage.usecase.ChangePasswordUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.mypage.model.PasswordModel
import com.dms.sms.feature.mypage.model.toDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class ChangePwViewModel(private val changePasswordUseCase: ChangePasswordUseCase) :
    BaseViewModel() {
    val currentPw = MutableLiveData<String>()
    val newPw = MutableLiveData<String>()
    val confirmPw = MutableLiveData<String>()

    val confirmPwErrorEvent = SingleLiveEvent<Unit>()
    val currentPwErrorEvent = SingleLiveEvent<Unit>()
    val successChangePw = SingleLiveEvent<Unit>()

    val pwMediatorLiveData = MediatorLiveData<Boolean>().apply {
        addSource(currentPw) { value = checkFullText() }
        addSource(newPw) { value = checkFullText() }
        addSource(confirmPw) { value = checkFullText() }
    }

    fun clickChangePw() {
        if (newPw.value.equals(confirmPw.value)) {
            val passwordRequest = PasswordModel(currentPw.value!!, newPw.value!!)
            changePasswordUseCase.execute(
                passwordRequest.toDomain(), object : DisposableSingleObserver<Result<Unit>>() {
                    override fun onSuccess(result: Result<Unit>) {
                        when (result) {
                            is Result.Success -> successChangePw()
                            is Result.Failure -> failChangePw(result)
                        }
                    }

                    override fun onError(e: Throwable) {
                        print(e.stackTrace)
                    }
                }, AndroidSchedulers.mainThread()
            )
        } else confirmPwErrorEvent.call()
    }

    private fun failChangePw(result: Result.Failure<Unit>) {
        when (result.reason) {
            Error.Conflict -> currentPwErrorEvent.call()
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.Unknown ->
                createToastEvent.value = "알 수 없는 오류 발생"
        }
    }

    private fun successChangePw() {
        createToastEvent.value = "비밀번호 변경 성공"
        successChangePw.call()
    }

    private fun checkFullText(): Boolean =
        !currentPw.value.isNullOrBlank() && !newPw.value.isNullOrBlank() && !confirmPw.value.isNullOrBlank()

    fun clickBack(){
        backEvent.call()
    }
}