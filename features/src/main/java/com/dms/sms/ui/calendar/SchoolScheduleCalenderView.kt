package com.dms.sms.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.ImageView
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
        beforeImv = previous_month_img
        afterImv = next_month_img
        titleTv = school_schedule_calender_title_tv
        daysGrid = school_schedule_calender_gv
        detailScheduleRecyclerView = detail_school_schedule_rv
        detailScheduleRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setOnClickEvent()
    }

    override lateinit var beforeImv: ImageView

    override lateinit var afterImv: ImageView

    override lateinit var titleTv: TextView

    override lateinit var daysGrid: GridView
    override lateinit var detailScheduleRecyclerView: RecyclerView


    override var selectedTv: TextView? = null
    override var tvColor: Int? = null

    override var eventListener: UserListener? = null

    override var calendar: Calendar = Calendar.getInstance()

    override val sdf: SimpleDateFormat = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)

    override var year: Int = 0

    override var month: Int = 0

    override var selectedDay: Int = 0

    private fun setOnClickEvent(){
        beforeImv.setOnClickListener {
            updateCalender(year, month-1)
            Log.d("year and month","$year and  $month")
        }
        afterImv.setOnClickListener {
            updateCalender(year, month+1)
            Log.d("year and month","$year and  $month")

        }
    }
    fun setCalender(date: Date){
        year= SimpleDateFormat("yyyy",Locale.KOREA).format(date).toInt()
        month = SimpleDateFormat("M",Locale.KOREA).format(date).toInt()
        detailScheduleRecyclerView.adapter= DetailScheduleAdapter(context,month)
        daysGrid.adapter = CalendarAdapter(this,
            detailScheduleRecyclerView.adapter as DetailScheduleAdapter, context,createCells(year,month))
        titleTv.text = "${year}년 ${month}월"
    }
    override fun selectDay(day: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        selectedDay = day

    }
    private fun updateCalender(year : Int , month : Int){
        val cells = createCells(year, month)
        daysGrid.adapter = CalendarAdapter(this,detailScheduleRecyclerView.adapter as DetailScheduleAdapter, context, cells)
        titleTv.text = "${this.year}년 ${this.month}월"

    }
    private fun createCells(year: Int, month: Int) : ArrayList<Day>{
        val cells= arrayListOf<Day>(Day("S"), Day("M"), Day("T"), Day("W"), Day("T"), Day("F"), Day("S"))


        when {
            month > 12 -> {
                this.year = year + 1
                this.month = 1
            }
            month < 1 -> {
                this.year = year - 1
                this.month = 12
            }
            else -> {
                this.year = year
                this.month = month
            }
        }

        calendar.time = sdf.parse("${year}년 ${month}월")!!

        (1 until calendar.get(Calendar.DAY_OF_WEEK)).forEach {
            cells.add(Day(0))
        }

        for (i in 1 .. calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            val day= Day(i, listOf(Pair(0,"공휴일"),Pair(1,"중간고사"),Pair(2,"의무귀가")))
            cells.add(day)
        }
        return cells
    }

}