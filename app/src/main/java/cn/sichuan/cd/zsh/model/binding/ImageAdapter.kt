package cn.sichuan.cd.zzsy.model.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


object ImageAdapter {

    @BindingAdapter(value = ["url", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String, placeholder: Int) {
        imageView.load(url) {
            placeholder(placeholder)
        }
    }

}