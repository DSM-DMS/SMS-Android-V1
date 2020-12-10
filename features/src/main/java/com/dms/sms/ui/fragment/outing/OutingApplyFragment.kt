package com.dms.sms.ui.fragment.outing

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingApplyBinding
import com.dms.sms.ui.dialog.OutingNoticeDialog
import com.dms.sms.viewmodel.outing.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class OutingApplyFragment : BaseFragment<FragmentOutingApplyBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_outing_apply

    override val viewModel by sharedViewModel<OutingApplyViewModel>()

//    outing_apply_btn.setOnClickListener {
//        navigateFragment(R.id.action_outingApplyFragment_to_outingCompleteFragment)
//    }

    override fun observeEvents() {
        with(viewModel) {
            dateEvent.observe(this@OutingApplyFragment, {
                val datePickerDialogListener =
                    DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                        outingDate.value = "${year}년 ${month + 1}월 ${date}일"
                        Log.d("disease",outingWithDisease.value.toString())
                    }
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    datePickerDialogListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            })

            startTimeEvent.observe(this@OutingApplyFragment, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        outingStartTime.value = "$hour : $minute "
                    }
                val timePickerDialog = TimePickerDialog(
                    requireContext(),
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timePickerDialogListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    android.text.format.DateFormat.is24HourFormat(requireContext())
                )
                timePickerDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                timePickerDialog.show()
            })

            endTimeEvent.observe(this@OutingApplyFragment, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        outingEndTime.value = "$hour : $minute "
                    }
                val timePickerDialog = TimePickerDialog(
                    requireContext(),
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timePickerDialogListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    android.text.format.DateFormat.is24HourFormat(requireContext())
                )
                timePickerDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                timePickerDialog.show()
            })

            diseaseEvent.observe(this@OutingApplyFragment, {
                val dialog = OutingNoticeDialog()
                dialog.show(requireActivity().supportFragmentManager, "OutingNoticeDialog")
            })

            onDiseaseCancelEvent.observe(this@OutingApplyFragment, {
                outingWithDisease.value = true
            })
        }
    }
}