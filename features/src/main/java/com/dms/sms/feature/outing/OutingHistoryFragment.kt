package com.dms.sms.feature.outing

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingHistoryBinding
import com.dms.sms.feature.outing.adapter.OutingHistoryAdapter
import com.dms.sms.feature.outing.viewmodel.OutingHistoryViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingHistoryFragment : BaseFragment<FragmentOutingHistoryBinding>() {
    override val viewModel:OutingHistoryViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outingHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.outingHistoryRecyclerView.adapter = OutingHistoryAdapter(viewModel)
    }

    override fun observeEvents() {
        viewModel.outingHistoryList.observe(viewLifecycleOwner, {
            binding.outingHistoryRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.recycler_item_slide_right)
            binding.outingHistoryRecyclerView.scheduleLayoutAnimation()
        })

        viewModel.clickOutingApplyEvent.observe(viewLifecycleOwner, {
            Log.e("click","clickcolci")
            navigateFragment(R.id.action_outingHistoryFragment_to_outingApplyFragment)
        })
    }

}