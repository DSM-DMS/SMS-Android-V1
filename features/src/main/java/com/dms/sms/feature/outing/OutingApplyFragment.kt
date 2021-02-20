package com.dms.sms.feature.outing

import android.app.TimePickerDialog
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingApplyBinding
import com.dms.sms.feature.outing.dialog.OutingNoticeDialog
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class OutingApplyFragment : BaseFragment<FragmentOutingApplyBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_outing_apply

    override val viewModel by sharedViewModel<OutingApplyViewModel>()

    override fun observeEvents() {
        with(viewModel) {
            outingPlace.value = null
            outingEndTime.value = null
            outingStartTime.value = null
            outingReason.value = null
            outingWithDisease.value = true

            startTimeEvent.observe(viewLifecycleOwner, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        startTime = "$hour:$minute:00"
                        when(compareTime("$hour:$minute:00",1)){
                            1,3 -> {
                                inputStartTime = "$hour:$minute:00"
                                outingStartTime.value = "${hour}시  ${minute}분"
                            }
                            2 -> createToastEvent.value = "외출은 4시 20분 이후에 가능합니다."
                            4 -> createToastEvent.value = "귀교 시간보다 늦을 수 없습니다."
                        }
                    }
                val timePickerDialog = TimePickerDialog(requireContext(),timePickerDialogListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),android.text.format.DateFormat.is24HourFormat(requireContext()))
                timePickerDialog.show()
            })

            endTimeEvent.observe(viewLifecycleOwner, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        endTime = "$hour:$minute:00"
                        when(compareTime("$hour:$minute:00",2)){
                            1,3 -> {
                                inputEndTime = "$hour:$minute:00"
                                outingEndTime.value = "${hour}시  ${minute}분 "
                            }
                            2 -> createToastEvent.value = "외출은 20시 30분 이후엔 불가능합니다."
                            4 -> createToastEvent.value = "외출 시간보다 빠를 수 없습니다."
                        }
                    }
                val timePickerDialog = TimePickerDialog( requireContext(),timePickerDialogListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),android.text.format.DateFormat.is24HourFormat(requireContext())
                )
                timePickerDialog.show()
            })

            diseaseEvent.observe(viewLifecycleOwner, {
                val dialog = OutingNoticeDialog()
                dialog.show(requireActivity().supportFragmentManager, "OutingNoticeDialog")
            })

            onDiseaseCancelEvent.observe(viewLifecycleOwner, {
                outingWithDisease.value = true
            })

            createOutingSuccessEvent.observe(viewLifecycleOwner, {
                navigateFragment(R.id.action_outingApplyFragment_to_outingCompleteFragment)
            })
            searchPlaceEvent.observe(viewLifecycleOwner, {
                val dialog = SearchPlaceFragment()
                dialog.show(requireActivity().supportFragmentManager, "SearchPlaceDialog")
            })
        }
    }
}