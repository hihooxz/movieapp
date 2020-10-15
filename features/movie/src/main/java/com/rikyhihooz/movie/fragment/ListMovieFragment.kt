package com.rikyhihooz.movie.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.rikyhihooz.common.adapter.MovieAdapter
import com.rikyhihooz.common.base.BaseFragment
import com.rikyhihooz.common.base.BaseViewModel
import com.rikyhihooz.common.utils.Constants.TAG_LISTMOVIE_FRAGMENT
import com.rikyhihooz.movie.R
import com.rikyhihooz.movie.databinding.FragmentListMovieBinding
import com.rikyhihooz.movie.viewmodel.ListMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

interface Callback{
    fun onCategory()
}

class ListMovieFragment : BaseFragment(), Callback, CategoryMovieFragment.onCallback {

    val vModel : ListMovieViewModel by viewModel()
    lateinit var binding:FragmentListMovieBinding
    override fun getViewModel(): BaseViewModel = vModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListMovieBinding.inflate(inflater, container, false).apply {
            binding = this
            callBack = this@ListMovieFragment
            viewModel=vModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        binding.recyclerView.adapter = MovieAdapter(vModel, TAG_LISTMOVIE_FRAGMENT){ vModel.onDetailMovie(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorit -> vModel.onFavoritMovie()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movie, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCategory() {
        CategoryMovieFragment().show(childFragmentManager, "")
    }

    override fun onShorting(key: Int) {
        vModel.getListMovie(key, 1, true)
    }
}