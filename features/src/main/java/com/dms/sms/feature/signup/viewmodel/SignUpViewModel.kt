package com.dms.sms.feature.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.domain.base.Result
import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.usecase.GetNoAccountStudentInfoUseCase
import com.dms.domain.signup.usecase.SignUpUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.signup.model.NoAccountStudentModel
import com.dms.sms.feature.signup.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import com.dms.domain.base.Error
import com.dms.sms.base.SingleLiveEvent
import com.dms.sms.feature.signup.model.SignUpInfoModel
import com.dms.sms.feature.signup.model.toDomain

class SignUpViewModel(
    private val getNoAccountStudentInfoUseCase: GetNoAccountStudentInfoUseCase,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {

    val verificationNumber = MutableLiveData<String>()

    private val _noAccountStudentInfo = MutableLiveData<NoAccountStudentModel>()
    val noAccountStudentInfo: LiveData<NoAccountStudentModel> get() = _noAccountStudentInfo

    val wrongVerificationNumberEvent = SingleLiveEvent<Unit>()

    val signUpSuccessEvent = SingleLiveEvent<Unit>()

    val noAccountStudentEvent = SingleLiveEvent<Unit>()

    val questToDmsEvent = SingleLiveEvent<Unit>()

    val duplicateIdEvent = SingleLiveEvent<Unit>()

    val cancelEvent = SingleLiveEvent<Unit>()


    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    fun cancel() {
        cancelEvent.call()
    }

    fun back() {
        cancelEvent.call()
        backEvent.call()
        verificationNumber.value = ""
    }

    fun questToDms() {
        questToDmsEvent.call()
    }

    fun getNoAccountStudent() {
        getNoAccountStudentInfoUseCase.execute(verificationNumber.value!!.toInt(), object :
            DisposableSingleObserver<Result<NoAccountStudent>>() {
            override fun onSuccess(result: Result<NoAccountStudent>) {
                when (result) {
                    is Result.Success -> {
                        noAccountStudentEvent.call()
                        _noAccountStudentInfo.value = result.value.toModel()
                    }

                    is Result.Failure -> when (result.reason) {
                        Error.NotFound -> wrongVerificationNumberEvent.call()
                        else -> createToastEvent.value = "오류 발생"
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, AndroidSchedulers.mainThread())
    }

    fun retryVerification() {
        verificationNumber.value = ""
        cancel()
    }

    fun signUp() {
        signUpUseCase.execute(SignUpInfoModel(
            verificationNumber.value!!.toInt(),
            idText.value!!, passwordText.value!!
        ).toDomain(), object : DisposableSingleObserver<Result<Unit>>(){
            override fun onSuccess(result: Result<Unit>) {
                when(result){
                    is Result.Success->{
                        signUpSuccessEvent.call()
                        initialize()
                    }
                    is Result.Failure->{
                        when(result.reason){
                            Error.Conflict -> duplicateIdEvent.call()
                            else -> createToastEvent.value = "아이디는 한글로 사용할 수 없습니다."
                        }
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, AndroidSchedulers.mainThread())
    }

    private fun initialize() {
        idText.value = ""
        passwordText.value = ""
        verificationNumber.value = ""
        _noAccountStudentInfo.value = NoAccountStudentModel("", -1, "", "", "")
    }
}