package com.zl.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import com.zl.ext.createViewBinding

abstract class BaseVBFragment <VB : ViewBinding> : BaseFragment() {

    private var _binding: VB? = null

     open val viewBind get() = _binding!!
     open var viewRoot: View? = null


    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 添加控件点击事件或添加监听器
     * */
    open fun initListener() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = createViewBinding(inflater, container, false)
        viewRoot = viewBind.root
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewRoot?.post {
            initView(savedInstanceState)
            initListener()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}