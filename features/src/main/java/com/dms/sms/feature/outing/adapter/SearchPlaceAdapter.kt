package com.dms.sms.feature.outing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemSearchPlaceBinding
import com.dms.sms.feature.outing.model.PlaceListModel
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel

class SearchPlaceAdapter(private val outingApplyViewModel: OutingApplyViewModel) : RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceViewHolder>() {
    private var searchPlaceListItems = ArrayList<PlaceListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlaceViewHolder {
        val binding = ItemSearchPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPlaceViewHolder, position: Int) {
        holder.bind(searchPlaceListItems[position],position)
    }

    override fun getItemCount(): Int =
        searchPlaceListItems.size

    fun setItems(searchPlaceList: MutableLiveData<ArrayList<PlaceListModel>>) {
        this.searchPlaceListItems = searchPlaceList.value!!
        notifyDataSetChanged()
    }

    inner class SearchPlaceViewHolder(private val binding: ItemSearchPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        private var position:Int? = 0

        fun bind(placeListModel: PlaceListModel, position: Int) {
            this.position = position
            binding.vh = this
            binding.placeModel = placeListModel
        }

        fun clickItem(v: View) {
            outingApplyViewModel.setSearchPlace(position!!)
        }
    }
}