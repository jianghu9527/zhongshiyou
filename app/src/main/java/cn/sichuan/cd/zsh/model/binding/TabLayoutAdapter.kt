package cn.sichuan.cd.zzsy.model.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

/**
 *   @author : ck
 *   time   : 2019/11/13
 */
object TabLayoutAdapter {

    @BindingAdapter(value = ["items"], requireAll = false)
    @JvmStatic
    fun setTabText(tabLayout: TabLayout, items: List<String>) {
        items.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
    }


    @BindingAdapter(value = ["tabItemClick"], requireAll = false)
    @JvmStatic
    fun tabItemClick(tabLayout: TabLayout, listener: TabLayout.OnTabSelectedListener) {
        tabLayout.addOnTabSelectedListener(listener)
    }


}