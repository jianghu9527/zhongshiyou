package cn.sichuan.cd.zsh.model.app.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import   cn.sichuan.cd.zsh.model.app.extend.flowLaunch

abstract class BaseVMFragment<VM : BaseViewModel, VB : ViewBinding> : BaseFragment<VB>() {

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createViewModel()
        lifecycle.addObserver(viewModel)
        //注册 UI事件
        registerDefUIChange()
        super.onViewCreated(view, savedInstanceState)
    }


    /**
     * 注册 UI 事件
     */
    private fun registerDefUIChange() {
//        viewModel.defUI.showDialog.observe(viewLifecycleOwner) {
//            showLoading()
//        }
//        viewModel.defUI.dismissDialog.observe(viewLifecycleOwner) {
//            dismissLoading()
//        }
//        viewModel.defUI.msgEvent.observe(viewLifecycleOwner) {
//            handleEvent(it)
//        }

        flowLaunch {
            viewModel.defUI.showDialog.flowWithLifecycle(lifecycle).collect {
                showLoading()
            }
        }
        flowLaunch {
            viewModel.defUI.dismissDialog.flowWithLifecycle(lifecycle).collect {
                dismissLoading()
            }
        }

        flowLaunch {
            viewModel.defUI.msgEvent.flowWithLifecycle(lifecycle).collect {
                handleEvent(it)
            }
        }

    }

    /**
     * 创建 ViewModel
     *
     * 共享 ViewModel的时候，重写  Fragment 的 getViewModelStore() 方法，
     * 返回 activity 的  ViewModelStore 或者 父 Fragment 的 ViewModelStore
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            viewModel = ViewModelProvider(this)[tClass] as VM
        }
    }
}