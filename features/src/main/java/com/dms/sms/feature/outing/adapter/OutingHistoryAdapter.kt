package com.dms.sms.feature.outing.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemOutingHistoryBinding
import com.dms.sms.feature.outing.model.OutingModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OutingHistoryAdapter: RecyclerView.Adapter<OutingHistoryAdapter.OutingHistoryViewHolder>() {
    private var outingListItems = ArrayList<OutingModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutingHistoryViewHolder {
        val binding = ItemOutingHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return OutingHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutingHistoryViewHolder, position: Int) {
        holder.bind(outingListItems[position])
    }

    override fun getItemCount(): Int =
        outingListItems.size

    fun setItems(outingHistoryList: MutableLiveData<ArrayList<OutingModel>>) {
        this.outingListItems = outingHistoryList.value!!
        notifyDataSetChanged()
    }

    inner class OutingHistoryViewHolder(private val binding: ItemOutingHistoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(outingModel: OutingModel) {
            val dv = (outingModel.startTime.toLong() * 1000)
            val df =  Date(dv)
            val ss = SimpleDateFormat("yyyy-MM-dd",Locale.KOREA).format(df)

            binding.outingHistoryDateTv.text = ss

            when(outingModel.outingStatus){
                "0","1","2" -> {
                    setOutingType("승인 대기","#FEDF42")
                }
                "3" -> {
                    setOutingType("외출중","#FF9100")
                }
                "4" -> {
                    setOutingType("만료","#5323B2")
                }
                "5" -> {
                    setOutingType("승인완료","#0DD214")
                    binding.goOutingHistoryOnlineCardTv.visibility = View.VISIBLE
                }
            }

            binding.outingListItems = outingModel
        }

        private fun setOutingType(outingHistoryState: String, color: String){
            binding.outingHistoryStateTv.text = outingHistoryState
            binding.outingApplyView.setBackgroundColor(Color.parseColor(color))
            binding.outingHistoryStateTv.setTextColor(Color.parseColor(color))
        }
    }

}