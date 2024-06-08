package cn.sichuan.cd.zsh.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sichuan.cd.zsh.databinding.HomeFragmentBinding


import cn.sichuan.cd.zsh.model.app.base.BaseVMFragment

import cn.sichuan.cd.zsh.ui.utils.GlideImageLoader
import cn.sichuan.cd.zsh.ui.network.entity.ArticlesBean
import cn.sichuan.cd.zsh.ui.network.entity.BannerBean
import cn.sichuan.cd.zsh.ui.detail.DetailActivity
import com.youth.banner.Banner
import cn.sichuan.cd.zsh.R
import cn.sichuan.cd.zsh.zsh.LogMangeUtil
import kotlinx.coroutines.launch


class HomeFragment : BaseVMFragment<HomeViewModel, HomeFragmentBinding>() {

    private val mAdapter by lazy { cn.sichuan.cd.zsh.ui.home.HomeListAdapter() }
    private var page: Int = 0
    private lateinit var banner: Banner<BannerBean, GlideImageLoader>

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {

        LogMangeUtil.d("---------HomeFragment-----initView--------it----")



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
                LogMangeUtil.d("-----------HomeFragment-------adapter---------")
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

        lifecycleScope.launch {

            viewModel.mBanners.collect {
                LogMangeUtil.d("--------------mBanners--------it----"+it.toString())
                banner.setDatas(it) }
        }
        lifecycleScope.launch {
            viewModel.projectData.collect {
                if (it.curPage == 1) mAdapter.setList(it.datas)
                else mAdapter.addData(it.datas)
                if (it.curPage == it.pageCount) mAdapter.loadMoreModule.loadMoreEnd()
                else mAdapter.loadMoreModule.loadMoreComplete()
                page = it.curPage
            }
        }
    }

    override fun lazyLoadData() {
        LogMangeUtil.d("--------------lazyLoadData--------------")
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
