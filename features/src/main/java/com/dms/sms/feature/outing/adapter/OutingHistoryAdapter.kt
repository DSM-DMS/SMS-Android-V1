package com.dms.sms.feature.outing.adapter

import android.graphics.Color
import android.view.LayoutInflater
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
            binding.outingHistoryDateTv.text = setDate(outingModel.startTime.toLong() * 1000).substring(0,11)
            binding.outingHistoryTimeTv.text = "${setDate(outingModel.startTime.toLong() * 1000).substring(11,16)} ~ ${setDate(outingModel.endTime.toLong() * 1000).substring(11,16)}"

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
                }
                "-1","-2" -> {
                    setOutingType("승인 거부","#F30404")
                }
            }

            binding.outingListItems = outingModel
        }

        private fun setOutingType(outingHistoryState: String, color: String){
            binding.outingHistoryStateTv.text = outingHistoryState
            binding.outingApplyView.setBackgroundColor(Color.parseColor(color))
            binding.outingHistoryStateTv.setTextColor(Color.parseColor(color))
        }

        private fun setDate(timeUnix: Long): String{
            val date = Date(timeUnix)
            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.KOREA)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return simpleDateFormat.format(date)
        }
    }

}