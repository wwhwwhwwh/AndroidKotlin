package com.zl.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zl.base.BaseVMFragment
import com.zl.demo.databinding.FragmentHomeBinding

class HomeFragment : BaseVMFragment<FragmentHomeBinding,HomeViewModel>() {

    private var _binding: FragmentHomeBinding? = null
    override fun initView(savedInstanceState: Bundle?) {
        val textView: TextView = viewBind.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

}