package com.dms.sms.feature.outing

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingAccessBinding
import com.dms.sms.feature.outing.dialog.OutingStartDialog
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingAccessFragment : BaseFragment<FragmentOutingAccessBinding>() {
    override val viewModel: OutingAccessViewModel by sharedViewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_access

    override fun observeEvents() {
        binding.noApplyOutingCardTv.text =
            Html.fromHtml(resources.getString(R.string.no_apply_outing_card_tv))

        with(viewModel) {
            outingStartDialogEvent.observe(viewLifecycleOwner, {
                val dialog = OutingStartDialog()
                dialog.show(requireActivity().supportFragmentManager, "OutingStartDialog")
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStudentUUID()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.clickBack()
            }
        })
    }

}