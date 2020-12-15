package com.dms.sms.feature.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.usecase.GetLoginDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel

class AutoLoginViewModel(
    private val getLoginDataUseCase: GetLoginDataUseCase,
) : BaseViewModel(){

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
                            autoLoginEvent.value = result.value != null && result.value!!.isAutoLoginChecked
                            Log.d("성공", result.value.toString())
                        }
                        is Result.Failure -> {
                            autoLoginEvent.value = false
                            Log.d("실패", "onSuccess")

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    autoLoginEvent.value = false
                    Log.d("실패", "onError")
                    e.printStackTrace()



                }

            },
            AndroidSchedulers.mainThread()
        )
    }
}