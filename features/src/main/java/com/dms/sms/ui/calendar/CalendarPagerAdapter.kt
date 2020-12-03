package com.dms.sms.ui.calendar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import java.util.*

class CalendarPagerAdapter(private val date : List<CalendarTime>,private var year: Int,private var month : Int, private val recyclerView: RecyclerView,private val scheduleList : List<Int>? = null ) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.calendar_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setDate(date[position].year, date[position].month)
        (holder.itemView as SchoolScheduleCalenderView).setCalender(year, month, recyclerView)
    }

    override fun getItemCount(): Int {
        return date.size
    }
    fun setDate(year: Int, month: Int){
        this.year = year
        this.month = month
    }
    fun getYear() : Int{
        return year
    }
    fun getMonth() : Int{
        return month
    }
}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


}