package com.dms.sms.feature.mypage.viewmodel

import com.dms.domain.auth.usecase.LoginDataDeleteUseCase
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class LogoutViewModel(private val loginDataDeleteUseCase: LoginDataDeleteUseCase) : BaseViewModel() {
    val logoutConfirm = SingleLiveEvent<Unit>()
    val logoutCancel = SingleLiveEvent<Unit>()

    fun clickConfirm() {
        loginDataDeleteUseCase.execute(Unit, object : DisposableSingleObserver<Result<Unit>>() {
            override fun onSuccess(result: Result<Unit>) {
                when(result) {
                    is Result.Success -> logoutConfirm.call()
                    is Result.Failure -> createToastEvent.value = "로그아웃에 실패하셨습니다."

                }
            }

            override fun onError(e: Throwable) {
                createToastEvent.value = "로그아웃에 실패하셨습니다."
            }
        }, AndroidSchedulers.mainThread())
    }
    fun clickCancel() = logoutCancel.call()
}