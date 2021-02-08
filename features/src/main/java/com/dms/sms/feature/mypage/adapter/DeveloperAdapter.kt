package com.dms.sms.feature.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemDeveloperBinding
import com.dms.sms.feature.mypage.model.DeveloperModel

class DeveloperAdapter : RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>() {
    private var developerItems = ArrayList<DeveloperModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val binding =
            ItemDeveloperBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DeveloperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) =
        holder.bind(developerItems[position])

    override fun getItemCount(): Int =
        developerItems.size

    fun setItems(developerList: MutableLiveData<ArrayList<DeveloperModel>>) {
        this.developerItems = developerList.value!!
        notifyDataSetChanged()
    }

    inner class DeveloperViewHolder(private val binding: ItemDeveloperBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(developerModel: DeveloperModel) {
            binding.developerModel = developerModel
        }
    }
}