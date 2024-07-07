package com.zl.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

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