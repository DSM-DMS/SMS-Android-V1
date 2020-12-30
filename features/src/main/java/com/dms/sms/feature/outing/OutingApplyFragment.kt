package com.dms.sms.feature.outing

import android.app.DatePickerDialog
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
            dateEvent.observe(viewLifecycleOwner, {
                val datePickerDialogListener =
                    DatePickerDialog.OnDateSetListener { _, year, month, date ->
                        viewModel.applyDate = "${year}/${month + 1}/${date} "
                        outingDate.value = "${year}년 ${month + 1}월 ${date}일"
                    }
                val datePickerDialog = DatePickerDialog(requireContext(),datePickerDialogListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            })

            startTimeEvent.observe(viewLifecycleOwner, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        startTime = "${hour + 9}:$minute:00"
                        outingStartTime.value = "${hour}시  ${minute}분"
                    }
                val timePickerDialog = TimePickerDialog(requireContext(),timePickerDialogListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),android.text.format.DateFormat.is24HourFormat(requireContext()))
                timePickerDialog.show()
            })

            endTimeEvent.observe(viewLifecycleOwner, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        endTime = "${hour + 9}:$minute:00"
                        outingEndTime.value = "${hour}시  ${minute}분 "
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