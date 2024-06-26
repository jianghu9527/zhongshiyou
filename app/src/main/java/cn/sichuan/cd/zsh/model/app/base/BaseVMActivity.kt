package cn.sichuan.cd.zzsy.model.app.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.sichuan.cd.zzsy.model.app.extend.flowLaunch
import java.lang.reflect.ParameterizedType

/**
 * @author : ck
 * @date : 2022/07/31 : 23:05
 */
abstract class BaseVMActivity<VM : BaseViewModel, VB : ViewBinding> : BaseActivity<VB>() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        createViewModel()
        lifecycle.addObserver(viewModel)
        //注册 UI事件
        registorDefUIChange()
        super.onCreate(savedInstanceState)
    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
//        viewModel.defUI.showDialog.observe(this) {
//            showLoading()
//        }
        flowLaunch {
            viewModel.defUI.showDialog.collect {
                showLoading()
            }
        }

//        viewModel.defUI.dismissDialog.observe(this) {
//            dismissLoading()
//        }
        flowLaunch {
            viewModel.defUI.dismissDialog.collect {
                dismissLoading()
            }
        }


        flowLaunch {
            viewModel.defUI.msgEvent.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[0] as Class<VM>
            viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)[cls]
        }
    }

}