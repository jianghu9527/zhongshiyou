package com.pcl.mvvm.ui.project

import android.content.Intent
import android.os.Bundle
import cn.sichuan.cd.zzsy.R
import cn.sichuan.cd.zzsy.databinding.ProjectFragmentBinding
import cn.sichuan.cd.zzsy.model.app.base.BaseVMFragment
import cn.sichuan.cd.zzsy.model.event.Message
import cn.sichuan.cd.zzsy.ui.detail.DetailActivity
import cn.sichuan.cd.zzsy.ui.project.ProjectViewModel

import cn.sichuan.cd.zzsy.ui.network.entity.ArticlesBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class ProjectFragment : BaseVMFragment<ProjectViewModel, ProjectFragmentBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }

    override val layoutId get() = R.layout.project_fragment

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.viewModel = viewModel
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun lazyLoadData() {
        viewModel.getFirstData()
    }

    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {
                val bean = msg.obj as ArticlesBean
                val intent = Intent().apply {
                    activity?.let { setClass(it, DetailActivity::class.java) }
                    putExtra("url", bean.link)
                }
                startActivity(intent)
            }
        }
    }
}
