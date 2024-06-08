package cn.sichuan.cd.zsh.ui.utils

import coil.load
import cn.sichuan.cd.zsh.ui.network.entity.BannerBean
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder


class GlideImageLoader : BannerImageAdapter<BannerBean>(null) {

    override fun onBindView(
        holder: BannerImageHolder?,
        data: BannerBean?,
        position: Int,
        size: Int
    ) {
        holder?.imageView?.load(data?.imagePath)
    }

}