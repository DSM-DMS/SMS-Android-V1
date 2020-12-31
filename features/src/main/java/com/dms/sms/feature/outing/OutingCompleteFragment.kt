package com.dms.sms.feature.outing

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dms.sms.R
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.main.fragment_outing_complete.*

class OutingCompleteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outing_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        outing_complete_btn.setOnClickListener {
            navigateFragment(R.id.action_outingCompleteFragment_to_mainFragment)
        }
        outing_apply_complete_tv.text = Html.fromHtml(resources.getString(R.string.outing_apply_complete_tv))
    }

}