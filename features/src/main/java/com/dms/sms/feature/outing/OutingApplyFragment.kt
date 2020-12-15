package com.dms.sms.feature.outing

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.core.view.get
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingApplyBinding
import com.dms.sms.feature.outing.dialog.OutingNoticeDialog
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
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
                        viewModel.applyDate = "${year}/${month + 1}/${date} "
//                        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//                        val mDate = simpleDateFormat.parse(applyDate)
//                        val timestamp = mDate!!.time
//
//                        Log.d("timeStamp",(timestamp/1000).toString())
                        outingDate.value = "${year}년 ${month + 1}월 ${date}일"
                    }
                val datePickerDialog = DatePickerDialog( requireContext(), datePickerDialogListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            })

            startTimeEvent.observe(this@OutingApplyFragment, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        viewModel.startTime = "${hour + 9}:$minute:00"
                        outingStartTime.value = "${hour}시 ${minute}분"

                        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        val mDate = simpleDateFormat.parse("${viewModel.applyDate} ${viewModel.startTime}")
                        val timestamp = mDate!!.time

                        Log.d("timeStamp",(timestamp/1000).toString())

                        Log.d("timeStatampapapap","${viewModel.applyDate} ${viewModel.startTime}")
                    }
                val timePickerDialog = TimePickerDialog( requireContext(), timePickerDialogListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(requireContext()))
                timePickerDialog.show()
            })

            endTimeEvent.observe(this@OutingApplyFragment, {
                val timePickerDialogListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        outingEndTime.value = "${hour}시 ${minute} "
                    }
                val timePickerDialog = TimePickerDialog( requireContext(), timePickerDialogListener,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(requireContext()))
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