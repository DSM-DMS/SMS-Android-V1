package com.dms.sms.feature.announcement.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.EndPointBaseFragment
import com.dms.sms.base.NoBackPressedBaseFragment
import com.dms.sms.databinding.FragmentAnnouncementsBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import com.dms.sms.navigateFragmentWithArgs
import com.dms.sms.ui.MainFragmentDirections
import kotlinx.android.synthetic.*
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnnouncementsFragment : BackPressedBaseFragment<FragmentAnnouncementsBinding>() {

    override val viewModel: AnnouncementsViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_announcements

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        initView()

    }

    private fun initView(){
        binding.searchAnnouncementEt.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                viewModel.onSearchPressed(binding.searchAnnouncementEt.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(viewModel.isSearched.value!!) {
                    viewModel.onBackPressed()
                }
                else {
                    remove()
                    requireActivity().onBackPressed()
                }
            }

        })
    }


    override fun observeEvents() {
        viewModel.announcementEvent.observe(viewLifecycleOwner, {
            requireActivity().navigateFragmentWithArgs(R.id.fragment_container, MainFragmentDirections.actionMainFragmentToAnnouncementDetailFragment(it))
        })

    }

}