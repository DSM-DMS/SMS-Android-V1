package com.dms.sms.feature.login

import androidx.lifecycle.MutableLiveData
import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.usecase.GetLoginDataUseCase
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class AutoLoginViewModel(
    private val getLoginDataUseCase: GetLoginDataUseCase,
) : BaseViewModel() {

    init {
        checkAutoLogin()
    }

    val autoLoginEvent = MutableLiveData<Boolean>()

    private fun checkAutoLogin() {
        getLoginDataUseCase.execute(
            Unit,
            object : DisposableSingleObserver<Result<LoggedInUser?>>() {
                override fun onSuccess(result: Result<LoggedInUser?>) {
                    when (result) {
                        is Result.Success -> {
                            autoLoginEvent.value =
                                result.value != null && result.value!!.isAutoLoginChecked
                        }
                        is Result.Failure -> {
                            autoLoginEvent.value = false

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    autoLoginEvent.value = false
                }

            },
            AndroidSchedulers.mainThread()
        )
    }
}