package com.dms.sms.feature.signup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dms.domain.account.entity.Student
import com.dms.domain.account.usecase.GetStudentUseCase
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.domain.auth.usecase.SaveLoginDataUseCase
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
import com.dms.sms.feature.login.model.LoggedInUserModel
import com.dms.sms.feature.login.model.LoginModel
import com.dms.sms.feature.login.model.toDomain
import com.dms.sms.feature.login.model.toEntity
import com.dms.sms.feature.signup.model.SignUpInfoModel
import com.dms.sms.feature.signup.model.toDomain

class SignUpViewModel(
    private val getNoAccountStudentInfoUseCase: GetNoAccountStudentInfoUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveLoginDataUseCase: SaveLoginDataUseCase,
    private val getStudentUseCase: GetStudentUseCase
) : BaseViewModel() {

    val verificationNumber = MutableLiveData<String>()

    private val _noAccountStudentInfo = MutableLiveData<NoAccountStudentModel>()
    val noAccountStudentInfo: LiveData<NoAccountStudentModel> get() = _noAccountStudentInfo

    val wrongVerificationNumberEvent = SingleLiveEvent<Unit>()

    val signUpSuccessEvent = SingleLiveEvent<Unit>()


    val loginFailedEvent = SingleLiveEvent<Unit>()

    val noAccountStudentEvent = SingleLiveEvent<Unit>()

    val questToDmsEvent = SingleLiveEvent<Unit>()

    val duplicateIdEvent = SingleLiveEvent<Unit>()

    val cancelEvent = SingleLiveEvent<Unit>()


    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    fun cancel() {
        cancelEvent.call()
    }

    fun login(){
        loginUseCase.execute(
            LoginModel(idText.value!!.trim(), passwordText.value!!.trim()).toDomain(),
            object : DisposableSingleObserver<Result<LoginResponse>>() {
                override fun onSuccess(result: Result<LoginResponse>) {
                    when (result) {
                        is Result.Success -> {
                            saveLoginData(result.value)
                            isConnectedWithParents(result)
                            initialize()
                        }
                        is Result.Failure -> {
                            loginFailedEvent.call()
                            initialize()
                        }
                    }

                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = "로그인 실패"
                    e.printStackTrace()
                }

            },
            AndroidSchedulers.mainThread()
        )
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
                        if(_noAccountStudentInfo.value!!.studentNumber.toInt()<10) {
                            _noAccountStudentInfo.value!!.studentNumber =
                                _noAccountStudentInfo.value!!.grade +
                                        _noAccountStudentInfo.value!!.group +"0"+ _noAccountStudentInfo.value!!.studentNumber
                        }
                        else{
                            _noAccountStudentInfo.value!!.studentNumber =
                                _noAccountStudentInfo.value!!.grade +
                                        _noAccountStudentInfo.value!!.group + _noAccountStudentInfo.value!!.studentNumber
                        }
                    }

                    is Result.Failure -> when (result.reason) {
                        Error.NotFound -> wrongVerificationNumberEvent.call()
                        else -> createSnackEvent.value = "오류 발생"
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
            idText.value!!.trim(), passwordText.value!!.trim()
        ).toDomain(), object : DisposableSingleObserver<Result<Unit>>(){
            override fun onSuccess(result: Result<Unit>) {
                when(result){
                    is Result.Success->{
                        login()
                        signUpSuccessEvent.call()
                    }
                    is Result.Failure->{
                        when(result.reason){
                            Error.Conflict -> duplicateIdEvent.call()
                            else -> createSnackEvent.value = "아이디는 한글로 사용할 수 없습니다."
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



    private fun saveLoginData(loginResponse: LoginResponse) {
        saveLoginDataUseCase.execute(
            LoggedInUserModel(
                loginResponse.accessToken, loginResponse.studentUUID,
                false
            ).toEntity(), object : DisposableSingleObserver<Result<Unit>>() {
                override fun onSuccess(result: Result<Unit>) {
                    when (result) {
                        is Result.Success -> {
                            Log.d("자동 로그인", "성공")
                        }
                        is Result.Failure -> {
                            createSnackEvent.value = "자동 로그인 설정이 실패했습니다."
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    createSnackEvent.value = "자동 로그인 설정이 실패했습니다."
                }
            }, AndroidSchedulers.mainThread()
        )
    }

    private fun isConnectedWithParents(result : Result.Success<LoginResponse>){
        getStudentUseCase.execute(result.value.studentUUID,object : DisposableSingleObserver<Result<Student>>(){
            override fun onSuccess(result: Result<Student>) {
                when(result){
                    is Result.Success->{
                        if(result.value.parentStatus=="CONNECTED")
                            createSnackEvent.value="학부모 계정과 연결되었습니다."
                        else if(result.value.parentStatus=="UN_CONNECTED")
                            createSnackEvent.value="현재 연결된 학부모 계정이 없습니다."

                    }
                    is Result.Failure-> createSnackEvent.value= "오류 발생"
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, AndroidSchedulers.mainThread())
    }


}