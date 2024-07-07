package com.zl.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment:Fragment() {

/*    lateinit var viewModel: VM
    private var _binding: VB? = null
    protected val viewBind get() = _binding!!
    protected var mVbRoot: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        binding.message.setText("hah")
//        return binding.root;
//        return inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("BaseFragment", javaClass.simpleName)
        val aClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        _binding = method.invoke(null, layoutInflater) as VB
        mVbRoot = viewBind.root
        return viewBind.root;
    }
    */











}