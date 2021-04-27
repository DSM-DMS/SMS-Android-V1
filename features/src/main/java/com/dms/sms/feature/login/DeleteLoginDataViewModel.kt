package com.dms.sms.feature.login

import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.usecase.GetLoginDataUseCase
import com.dms.domain.auth.usecase.LoginDataDeleteUseCase
import com.dms.domain.base.Result
import com.dms.sms.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class DeleteLoginDataViewModel(private val getLoginDataUseCase: GetLoginDataUseCase, private val loginDataDeleteUseCase: LoginDataDeleteUseCase) : BaseViewModel(){

    fun deleteLoginData(){
        getLoginDataUseCase.execute(Unit, object : DisposableSingleObserver<Result<LoggedInUser?>>(){
            override fun onSuccess(result: Result<LoggedInUser?>) {
                when(result){
                    is Result.Success ->{
                        if(!result.value!!.isAutoLoginChecked){
                            loginDataDeleteUseCase.execute(Unit, object : DisposableSingleObserver<Result<Unit>>(){
                                override fun onSuccess(t: Result<Unit>) {
                                }

                                override fun onError(e: Throwable) {
                                }
                            }, AndroidSchedulers.mainThread() )
                        }
                        else{
                        }
                    }
                    is Result.Failure ->{
                    }
                }
            }

            override fun onError(e: Throwable) {

            }

        },AndroidSchedulers.mainThread())
    }
}