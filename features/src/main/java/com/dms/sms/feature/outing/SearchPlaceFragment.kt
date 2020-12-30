package com.dms.sms.feature.outing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentSearchPlaceBinding
import com.dms.sms.feature.outing.adapter.SearchPlaceAdapter
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPlaceFragment : BaseDialog<FragmentSearchPlaceBinding>() {
    override val viewModel: OutingApplyViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_search_place

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchPlaceRecyclerView.adapter = SearchPlaceAdapter()
        binding.searchPlaceRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    override fun observeEvent() {
    }

}