package com.zhonglv.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>:Fragment() {

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

    lateinit var viewModel: VM

    private var _binding: VB? = null
    protected val viewBind get() = _binding!!

    private var viewRoot: View? = null

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 添加控件点击事件或添加监听器
     * */
    open fun onBindViewClick() {}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = createViewBinding(inflater, container, false)
        viewRoot = viewBind.root
        viewModel = createViewModel()
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewRoot?.post {
            initView(savedInstanceState)
            onBindViewClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
    }

    /**
     * 创建viewbinding对象
     * @param layoutInflater layout解析器
     * @param parent 父容器
     * @param attachToParent 是否添加进父容器
     * */
    fun <VB : ViewBinding> Fragment.createViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): VB =
        viewBindingClass<VB>(this) { clazz ->
            clazz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            ).invoke(null, layoutInflater, parent, attachToParent) as VB
        }

    /**
     * 获取viewbinding泛型的class对象
     * @param any viewbinding泛型父类的对象
     * @param actualPosi 泛型的序号，从0开始
     * @param block 获取泛型后的回调
     * */
    fun <VB : ViewBinding> viewBindingClass(
        any: Any,
        actualPosi: Int = 0,
        block: (Class<VB>) -> VB
    ): VB {
        val genericSuperclass = any.javaClass.genericSuperclass
        val superclass = any.javaClass.superclass
        while (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                try {
                    return block.invoke(genericSuperclass.actualTypeArguments[actualPosi] as Class<VB>)
                } catch (thr: Throwable) {
                    throw thr
                }
            }
        }
        throw IllegalArgumentException("未找到ViewBinding泛型")
    }

}