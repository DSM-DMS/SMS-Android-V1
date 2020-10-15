package com.dms.sms.ui.fragment.outing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.sms.R
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.main.fragment_outing_apply.*

class OutingApplyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outing_apply, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        outing_apply_btn.setOnClickListener {
            navigateFragment(R.id.action_outingApplyFragment_to_outingCompleteFragment)
        }

    }

}