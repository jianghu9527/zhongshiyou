package cn.sichuan.cd.zzsy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment


import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.PermissionUtils

import cn.sichuan.cd.zzsy.model.app.base.BaseVMActivity
import cn.sichuan.cd.zzsy.model.app.base.NoViewModel
import cn.sichuan.cd.zzsy.ui.home.HomeFragment
import cn.sichuan.cd.zzsy.ui.me.MeFragment
import cn.sichuan.cd.zzsy.R
import  cn.sichuan.cd.zzsy.databinding.ActivityMainBinding
import cn.sichuan.cd.zzsy.zsh.LogMangeUtil

import com.pcl.mvvm.ui.project.ProjectFragment


class MainActivity : BaseVMActivity<NoViewModel, ActivityMainBinding>() {

    private val fragments = ArrayList<Fragment>()
    private lateinit var showFragment: Fragment


    override fun initView(savedInstanceState: Bundle?) {
        LogMangeUtil.d("-----------------MainActivity----------initView-----------")
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.colorPrimary))
        fragments.add(HomeFragment.newInstance())
        fragments.add(ProjectFragment.newInstance())
        fragments.add(MeFragment.newInstance())
        showFragment = fragments[0]
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, showFragment)
            .commitNow()

        mBinding.bottomNavigationView.setOnItemSelectedListener {
            switchPage(it.itemId)
            return@setOnItemSelectedListener true
        }
        PermissionUtils.permission(*PermissionUtils.getPermissions().toTypedArray())
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {

                }

                override fun onDenied(
                    forever: MutableList<String>, denied: MutableList<String>
                ) {

                }

            })
            .request()
    }

    private fun switchPage(itemId: Int) {
        val index = when (itemId) {
            R.id.action_home -> 0
            R.id.action_project -> 1
            R.id.action_me -> 2
            else -> return
        }
        val now = fragments[index]
        supportFragmentManager.beginTransaction().apply {
            if (!now.isAdded) {
                add(R.id.container, now)
            }
            hide(showFragment)
            show(now)
            showFragment = now
            commit()
        }
    }

    override fun initData() {
    }
}
