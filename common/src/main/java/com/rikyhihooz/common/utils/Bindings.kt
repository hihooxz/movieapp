package com.rikyhihooz.common.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rikyhihooz.common.BuildConfig
import com.rikyhihooz.common.R
import com.rikyhihooz.common.adapter.CategoryMovieAdapter
import com.rikyhihooz.common.adapter.MovieAdapter
import com.rikyhihooz.common.utils.Constants.TAG_LISTMOVIE_FRAGMENT
import com.rikyhihooz.model.CategoryMovie
import com.rikyhihooz.model.Result

object Bindings {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(i: ImageView, url: String?) {
        i.apply {
            Glide.with(context).load(BuildConfig.BASE_URL_IMAGE + url)
                .placeholder(R.drawable.ic_place_holder).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("app:dateRelease")
    fun setReleaseDate(i: TextView, date: String?) {
        i.apply {
            text = if (date != null) DateConverter.dateConterver(
                date,
                DateConverter.yyyy_MM_dd,
                DateConverter.EEE_ddMMMyyyy
            ) else ""
        }
    }

    @JvmStatic
    @BindingAdapter("app:dataMovie")
    fun setDataMovie1(r: RecyclerView, data: List<Result>?) {
        data.let {
            (r.adapter as MovieAdapter).submitList(data)
        }
    }

    @JvmStatic
    @BindingAdapter("app:onFavorit", "app:tagFavorit", requireAll = false)
    fun setFavorit(i:ImageView, isFavorite:Boolean, tag:String?){
        i.apply {
            visibility = if(tag == TAG_LISTMOVIE_FRAGMENT) View.INVISIBLE else View.VISIBLE
            if(isFavorite){
                setImageResource(R.drawable.ic_favorite_24)
                setColorFilter(ContextCompat.getColor(context, R.color.red))
            }else{
                setImageResource(R.drawable.ic_favorite_border_24)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:dataCategoryMovie")
    fun setDataCategory(r:RecyclerView, data:List<CategoryMovie>?){
        r.apply {
            data.let { (r.adapter as CategoryMovieAdapter).submitList(it) }
        }
    }

}