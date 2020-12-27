package com.dms.sms.feature.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.sms.R
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.main.fragment_my_page.*


class MyPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        change_password_card.setOnClickListener {
            requireActivity().navigateFragment(R.id.fragment_container, R.id.action_mainFragment_to_changePasswordFragment)
        }
        developers_introducing_card.setOnClickListener {
            requireActivity().navigateFragment(R.id.fragment_container, R.id.action_mainFragment_to_introducingDevelopersFragment)
        }
    }


}