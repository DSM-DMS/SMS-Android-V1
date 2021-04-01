package com.dms.sms.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dms.sms.BR
import com.dms.sms.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import splitties.toast.toast

abstract class BackPressedBaseFragment <T : ViewDataBinding> : Fragment(){

    lateinit var binding : T
    abstract val viewModel : BaseViewModel

    abstract val layoutId : Int

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
                Log.d("smsonatt",findNavController().currentDestination.toString())

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        backButtonSubjectDisposable.dispose()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.vm, viewModel)

        viewModel.createSnackEvent.observe(viewLifecycleOwner, {
            Snackbar.make(view.rootView, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.createToastEvent.observe(viewLifecycleOwner, {
            toast(it)
        })
        viewModel.backEvent.observe(this, {
            Navigation.findNavController(requireView()).popBackStack()
        })
        viewModel.expiredTokenEvent.observe(viewLifecycleOwner,{
            requireActivity().findNavController(R.id.fragment_container).navigate(R.id.action_mainFragment_to_logInFragment)
        })
        observeEvents()
    }

    abstract fun observeEvents()

}