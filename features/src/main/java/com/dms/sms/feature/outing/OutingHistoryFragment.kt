package com.dms.sms.feature.outing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingHistoryBinding
import com.dms.sms.feature.outing.adapter.OutingHistoryAdapter
import com.dms.sms.feature.outing.viewmodel.OutingHistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingHistoryFragment : BaseFragment<FragmentOutingHistoryBinding>() {
    override val viewModel:OutingHistoryViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outingHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.outingHistoryRecyclerView.adapter = OutingHistoryAdapter()
    }

    override fun observeEvents() {
    }

}