package com.rikyhihooz.movie.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rikyhihooz.model.ResultReview
import com.rikyhihooz.movie.adapter.ReviewAdapter

object Bindings {

    @JvmStatic
    @BindingAdapter("app:dataReview")
    fun setDataReview(r:RecyclerView, data:List<ResultReview>?){
        data.let { (r.adapter as ReviewAdapter).submitList(data) }
    }
}