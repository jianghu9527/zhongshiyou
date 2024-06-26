package cn.sichuan.cd.zzsy.ui.me

import android.os.Bundle
import cn.sichuan.cd.zzsy.R
import cn.sichuan.cd.zzsy.databinding.MeFragmentBinding

import cn.sichuan.cd.zzsy.model.app.base.BaseVMFragment


class MeFragment : BaseVMFragment<MeViewModel, MeFragmentBinding>() {

//    private val mAdapter by lazy { MeWebAdapter() }

    companion object {
        fun newInstance() = MeFragment()
    }

    override val layoutId get() = R.layout.me_fragment

    override fun initView(savedInstanceState: Bundle?) {
//        with(mBinding.rvMeUesdWeb) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = mAdapter
//        }
//        mAdapter.setOnItemClickListener { _, _, position ->
//            val intent = Intent().apply {
//                setClass(requireContext(), DetailActivity::class.java)
//                putExtra("url", (mAdapter.data[position]).link)
//            }
//            startActivity(intent)
//        }

//        lifecycleScope.launchWhenCreated {
//            viewModel.popularWeb.collect {
//                mAdapter.setList(it)
//            }
//        }
    }

    override fun lazyLoadData() {
        viewModel.getPopularWeb()
    }
}
