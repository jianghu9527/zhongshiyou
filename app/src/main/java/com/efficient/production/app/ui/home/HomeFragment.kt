package com.efficient.production.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.efficient.production.app.databinding.HomeFragmentBinding


import com.efficient.production.app.model.app.base.BaseVMFragment

import com.efficient.production.app.ui.utils.GlideImageLoader
import com.pcl.mvvm.network.entity.ArticlesBean
import com.pcl.mvvm.network.entity.BannerBean
import com.efficient.production.app.ui.detail.DetailActivity
import com.youth.banner.Banner
import com.efficient.production.app.R



class HomeFragment : BaseVMFragment<HomeViewModel, HomeFragmentBinding>() {

    private val mAdapter by lazy { com.efficient.production.app.ui.home.HomeListAdapter() }
    private var page: Int = 0
    private lateinit var banner: Banner<BannerBean, GlideImageLoader>

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding.rvHome) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            //banner
            banner = Banner(context)
            banner.minimumWidth = MATCH_PARENT
            banner.layoutParams =
                ViewGroup.LayoutParams(MATCH_PARENT, resources.getDimension(R.dimen.dp_120).toInt())
            banner.setAdapter(GlideImageLoader())
        }
        mAdapter.apply {
            addHeaderView(banner)
            loadMoreModule.setOnLoadMoreListener(this@HomeFragment::loadMore)
            setOnItemClickListener { adapter, _, position ->
                val item = adapter.data[position] as ArticlesBean
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("url", item.link)
                startActivity(intent)
            }
        }
        mBinding.refreshHome.setOnRefreshListener {
            dropDownRefresh()
        }
    }

    override fun initObserve() {
        viewModel.refreshState.observe(this@HomeFragment) {
            if (mBinding.refreshHome.isRefreshing) mBinding.refreshHome.finishRefresh()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.mBanners.collect { banner.setDatas(it) }
        }
//        lifecycleScope.launchWhenCreated {
//            viewModel.projectData.collect {
//                if (it.curPage == 1) mAdapter.setList(it.datas)
//                else mAdapter.addData(it.datas)
//                if (it.curPage == it.pageCount) mAdapter.loadMoreModule.loadMoreEnd()
//                else mAdapter.loadMoreModule.loadMoreComplete()
//                page = it.curPage
//            }
//        }
    }

    override fun lazyLoadData() {
        viewModel.getBanner()
        viewModel.getHomeList(page)
    }

    /**
     * 下拉刷新
     */
    private fun dropDownRefresh() {
        page = 0
        viewModel.getHomeList(page, true)
        viewModel.getBanner(true)
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        viewModel.getHomeList(page + 1)
    }

}
