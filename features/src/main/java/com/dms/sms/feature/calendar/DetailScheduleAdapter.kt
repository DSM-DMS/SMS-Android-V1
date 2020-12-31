package com.dms.sms.feature.calendar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import kotlinx.android.synthetic.main.item_detail_school_schedule.view.*

class DetailScheduleAdapter(private val context: Context,private val month : Int,var date : Any? = null, var scheduleList : List<Pair<Int?,String?>?>? = listOf()) : RecyclerView.Adapter<DetailScheduleAdapter.ViewHolder>(){

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private val view = v
        fun bind(item : Pair<Int?,String?>?){
            setSpecialDay(view, item!!.first!!)
            view.schedule_tv.text =item.second
            view.date_tv.text ="${month}/${date}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_school_schedule,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scheduleList!![position])
    }
    override fun getItemCount(): Int = scheduleList!!.size

    private fun setSpecialDay(view: View, speciality : Int){
        when(speciality){
            1-> view.event_view.backgroundTintList = context.resources.getColorStateList(R.color.colorGreen, null)
            2-> view.event_view.backgroundTintList = context.resources.getColorStateList(R.color.colorPurple, null)
        }


    }

}