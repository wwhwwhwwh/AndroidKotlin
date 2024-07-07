package com.zl.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {


    private var _binding: VB? = null

    protected val viewBind get() = _binding!!
    protected var viewRoot: View? = null


    protected abstract fun initView(savedInstanceState: Bundle?)



    /**
     * 添加控件点击事件或添加监听器
     * */
    open fun initListener() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBind()
        setContentView(viewRoot)

        viewRoot?.post {
            //初始化View
            initView(savedInstanceState)
            // 设置点击事件
            initListener();
        }

    }

    /**
     * 创建ViewBinding
     * 利用反射 根据泛型得到 ViewBinding
     */
    private fun initViewBind() {
        Log.d("BaseActivity", javaClass.simpleName) //获取当前实例的Class对象，相当于在Java中调用 getClass()方法
        // 原来的写法 binding = ActivityTestBinding.inflate(layoutInflater)
        val aClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        _binding = method.invoke(null, layoutInflater) as VB
        viewRoot = viewBind.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}