package com.pcl.mvvm.ui.project

import android.content.Intent
import android.os.Bundle
import com.efficient.production.app.R
import com.efficient.production.app.databinding.ProjectFragmentBinding
import com.efficient.production.app.model.app.base.BaseVMFragment
import com.efficient.production.app.model.event.Message
import com.efficient.production.app.ui.detail.DetailActivity
import com.efficient.production.app.ui.project.ProjectViewModel

import com.efficient.production.app.ui.network.entity.ArticlesBean
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
