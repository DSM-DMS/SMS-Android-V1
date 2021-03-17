package com.dms.sms.feature.outing

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentSearchPlaceBinding
import com.dms.sms.feature.outing.adapter.SearchPlaceAdapter
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchPlaceFragment : BaseDialog<FragmentSearchPlaceBinding>() {
    override val viewModel by sharedViewModel<OutingApplyViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_search_place

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchPlaceRecyclerView.adapter = SearchPlaceAdapter(viewModel)
        binding.searchPlaceRecyclerView.layoutManager = LinearLayoutManager(context)

        initView()

    }

    override fun observeEvent() {
        with(viewModel) {
            searchPlaceEt.value = null
            searchPlaceList.value = ArrayList(emptyList())

            searchPlaceItemEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }
    }

    private fun initView() {
        binding.searchPlaceEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onSearchPressed(binding.searchPlaceEt.text.trim().toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

}