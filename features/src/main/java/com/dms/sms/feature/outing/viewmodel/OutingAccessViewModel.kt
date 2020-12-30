package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.domain.base.Error
import com.dms.domain.base.Result
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.usecase.GetDetailOutingUseCase
import com.dms.domain.outing.usecase.GetOutingUUIDUseCase
import com.dms.sms.base.BaseViewModel
import com.dms.sms.feature.outing.model.DetailOutingModel
import com.dms.sms.feature.outing.model.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

class OutingAccessViewModel(
    private val getDetailOutingUseCase: GetDetailOutingUseCase,
    private val getOutingUUIDUseCase: GetOutingUUIDUseCase
) : BaseViewModel() {

    val detailOutingData = MutableLiveData<DetailOutingModel>()
    val accessResult = MutableLiveData(true)

    init {
        getDetailOuting()
    }

    private fun getDetailOuting() {
        val outingUUID = getOutingUUIDUseCase.getUUID("outingUUID")

        getDetailOutingUseCase.execute(
            outingUUID,
            object : DisposableSingleObserver<Result<DetailOutingResponse>>() {
                override fun onSuccess(result: Result<DetailOutingResponse>) {
                    when (result) {
                        is Result.Success -> {
                            accessResult.value = false
                            detailOutingData.value = result.value.toModel()
                        }
                        is Result.Failure -> {
                            accessResult.value = true
                            failDetailOuting(result)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                }
            },
            AndroidSchedulers.mainThread()
        )
    }

    private fun failDetailOuting(result: Result.Failure<DetailOutingResponse>) {
        when (result.reason) {
            Error.InternalServer ->
                createToastEvent.value = "서버 오류 발생"
            Error.Network ->
                createToastEvent.value = "네트워크 오류 발생"
            Error.BadRequest ->
                createToastEvent.value = "외출 시간을 다시 확인해주세요"
            Error.UnAuthorized ->
                createToastEvent.value = "외출증 생성 실패"
            Error.Forbidden ->
                createToastEvent.value = "외출증 생성 실패"
            Error.NotFound ->
                createToastEvent.value = "존재하지 않는 외출증 입니다."
            Error.Timeout ->
                createToastEvent.value = "요청하는데 시간이 너무 오래 걸립니다."
            Error.Unknown ->
                createToastEvent.value = "알 수 없는 오류 발생"

        }
    }
}