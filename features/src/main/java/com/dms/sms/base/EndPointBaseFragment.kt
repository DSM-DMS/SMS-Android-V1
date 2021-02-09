package com.dms.sms.base

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import splitties.toast.toast

abstract class EndPointBaseFragment<T : ViewDataBinding> : BaseFragment<T>() {

    val backButtonSubject: Subject<Long> =
        BehaviorSubject.createDefault(0L)
            .toSerialized() //thread safe를 위해 사용

    private val backButtonSubjectDisposable: Disposable =
        backButtonSubject.buffer(2, 1)
            .map { it[0] to it[1] } //Pair로 변환
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.second - it.first <= 2000) activity?.finish()
                else toast("뒤로가기 버튼을 한 번 더 누르시면 종료됩니다.")
            }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backButtonSubject.onNext(System.currentTimeMillis())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        backButtonSubjectDisposable.dispose()
    }

}