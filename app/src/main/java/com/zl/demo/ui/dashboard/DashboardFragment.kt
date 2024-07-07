package com.zl.demo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zl.base.BaseVBFragment
import com.zl.base.BaseVMFragment
import com.zl.demo.databinding.FragmentDashboardBinding

class DashboardFragment : BaseVMFragment<FragmentDashboardBinding,DashboardViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

        val textView: TextView = viewBind.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }


}