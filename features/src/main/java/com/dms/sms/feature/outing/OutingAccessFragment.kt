package com.dms.sms.feature.outing

import android.text.Html
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingAccessBinding
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingAccessFragment : BaseFragment<FragmentOutingAccessBinding>() {
    override val viewModel: OutingAccessViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_access

    override fun observeEvents() {
        binding.noApplyOutingCardTv.text = Html.fromHtml(resources.getString(R.string.no_apply_outing_card_tv))
    }

}