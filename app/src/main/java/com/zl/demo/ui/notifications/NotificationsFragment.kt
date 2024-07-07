package com.zl.demo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zl.base.BaseVMFragment
import com.zl.demo.databinding.FragmentHomeBinding
import com.zl.demo.databinding.FragmentNotificationsBinding
import com.zl.demo.ui.home.HomeViewModel

class NotificationsFragment : BaseVMFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    private var _binding: FragmentNotificationsBinding? = null
    override fun initView(savedInstanceState: Bundle?) {
        val textView: TextView = viewBind.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}