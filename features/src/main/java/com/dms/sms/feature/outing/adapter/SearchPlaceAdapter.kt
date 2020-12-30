package com.dms.sms.feature.outing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemSearchPlaceBinding
import com.dms.sms.feature.outing.model.PlaceListModel

class SearchPlaceAdapter : RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceViewHolder>() {
    private var searchPlaceListItems = ArrayList<PlaceListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlaceViewHolder {
        val binding = ItemSearchPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPlaceViewHolder, position: Int) {
        holder.bind(searchPlaceListItems[position])
    }

    override fun getItemCount(): Int =
        searchPlaceListItems.size

    fun setItems(searchPlaceList: MutableLiveData<ArrayList<PlaceListModel>>) {
        this.searchPlaceListItems = searchPlaceList.value!!
        notifyDataSetChanged()
    }

    inner class SearchPlaceViewHolder(private val binding: ItemSearchPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(placeListModel: PlaceListModel) {
            binding.placeModel = placeListModel
        }
    }
}