package com.dms.sms.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import kotlinx.android.synthetic.main.calender_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SchoolScheduleCalenderView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs), CalendarDaysListener {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calender_view, this)
        assignUiElements()
    }
    private fun assignUiElements(){
        daysGrid = school_schedule_calender_gv
    }

    override lateinit var daysGrid: GridView

    override var selectedTv: TextView? = null

    override var tvColor: Int? = null

    override var calendar: Calendar = Calendar.getInstance()

    override val sdf: SimpleDateFormat = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)

    override var year: Int = 0

    override var month: Int = 0

    override var selectedDay: Int = 0

    private lateinit var detailScheduleRecyclerView : RecyclerView

    fun setCalender(year: Int, month: Int, recyclerView: RecyclerView){
        this.year = year
        this.month = month
        setRecyclerView(recyclerView)
        detailScheduleRecyclerView.adapter= DetailScheduleAdapter(context,month)
        detailScheduleRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        daysGrid.adapter = CalendarAdapter(this,detailScheduleRecyclerView.adapter as DetailScheduleAdapter, context,createCells(year,month))
    }
//    fun setDetailSchedule(){
//        detailScheduleRecyclerView.adapter= DetailScheduleAdapter(context,month)
//        daysGrid.adapter = CalendarAdapter(this,
//                detailScheduleRecyclerView.adapter as DetailScheduleAdapter, context,createCells(year,month))
//    }
    fun updateCalender(year : Int , month : Int){
        val cells = createCells(year, month)
        daysGrid.adapter = CalendarAdapter(this,detailScheduleRecyclerView.adapter as DetailScheduleAdapter, context, cells)
    }
    private fun setRecyclerView(recyclerView: RecyclerView){
        detailScheduleRecyclerView = recyclerView
    }
    override fun selectDay(day: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        selectedDay = day

    }

    private fun createCells(year: Int, month: Int) : ArrayList<Day>{
        val cells= arrayListOf(Day("S"), Day("M"), Day("T"), Day("W"), Day("T"), Day("F"), Day("S"))
//        val (y, m) = calculateTime(year, month)
//        this.year = y
//        this.month = m


        calendar.time = sdf.parse("${year}년 ${month}월")!!

        (1 until calendar.get(Calendar.DAY_OF_WEEK)).forEach {
            cells.add(Day(0))
        }

        for (i in 1 .. calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            val day= Day(i, listOf(Pair(1,"중간고사"),Pair(2,"의무귀가")))
            cells.add(day)
        }
        return cells
    }

}