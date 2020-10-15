package com.rikyhihooz.favoritmovie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikyhihooz.common.adapter.MovieAdapter
import com.rikyhihooz.common.base.BaseFragment
import com.rikyhihooz.common.base.BaseViewModel
import com.rikyhihooz.common.utils.Constants.TAG_LISTMOVIE_FAVORIT_FRAGMENT
import com.rikyhihooz.favoritmovie.R
import com.rikyhihooz.favoritmovie.databinding.FragmentListFavoritMovieBinding
import com.rikyhihooz.favoritmovie.viewmodel.FavoriteMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListFavoritMovieFragment : BaseFragment() {

    val vModel : FavoriteMovieViewModel by viewModel()
    lateinit var binding:FragmentListFavoritMovieBinding

    override fun getViewModel(): BaseViewModel = vModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListFavoritMovieBinding.inflate(inflater, container, false).apply {
            viewModel = vModel
            binding = this
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.toolbar.init()
        binding.toolbar.title = getString(R.string.label_favoritemovie)
        binding.recyclerView.adapter = MovieAdapter(vModel, TAG_LISTMOVIE_FAVORIT_FRAGMENT){ vModel.onDetailMovie(it) }

    }

}