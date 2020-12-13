package nh.hackaton.forkids.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import nh.hackaton.forkids.util.AppUtil.THUMBNAIL_URL

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bindThumbnail")
    fun bindThumbnail(view: ImageView, imageUrl: String?) {

        Glide.with(view.context)
                .load(THUMBNAIL_URL(imageUrl))
                .fitCenter()
                .into(view)
    }

}