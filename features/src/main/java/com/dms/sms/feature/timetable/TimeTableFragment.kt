package com.dms.sms.feature.timetable

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.domain.util.dayOfWeek
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.databinding.FragmentTimeTableBinding
import com.dms.sms.feature.timetable.adapter.TimeTableAdapter
import com.dms.sms.feature.timetable.viewmodel.TimeTableViewModel
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.main.fragment_school_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimeTableFragment : BackPressedBaseFragment<FragmentTimeTableBinding>() {

    override val viewModel: TimeTableViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_time_table

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        bindToAdapter(binding.timeTableMonRecyclerView)
        bindToAdapter(binding.timeTableTueRecyclerView)
        bindToAdapter(binding.timeTableWedRecyclerView)
        bindToAdapter(binding.timeTableThuRecyclerView)
        bindToAdapter(binding.timeTableFriRecyclerView)
    }

    override fun observeEvents() {
        calendar_switch_btn.setOnClickListener {
            navigateFragment(R.id.action_global_schoolScheduleFragment)
        }
    }

    private fun bindToAdapter(rc: RecyclerView){
        rc.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        rc.adapter = TimeTableAdapter()
    }

    private fun initView() {
        when(dayOfWeek()){
            "월요일" -> setDayBackground(binding.monTv)
            "화요일" -> setDayBackground(binding.tueTv)
            "수요일" -> setDayBackground(binding.wedTv)
            "목요일" -> setDayBackground(binding.thuTv)
            "금요일" -> setDayBackground(binding.friTv)
        }
    }

    private fun setDayBackground(tv: TextView){
        tv.setTextColor(resources.getColor(R.color.colorWhite))
        tv.setBackgroundResource(R.drawable.little_purple_gray_view)
    }
}