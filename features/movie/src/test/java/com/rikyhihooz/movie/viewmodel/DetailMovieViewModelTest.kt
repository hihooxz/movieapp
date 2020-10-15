package com.rikyhihooz.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.rikyhihooz.common_test.dataset.MovieDataset.FAKE_REVIEW
import com.rikyhihooz.model.ReviewMovie
import com.rikyhihooz.movie.service.MovieService
import com.rikyhihooz.repository.utils.AppDispatchers
import com.rikyhihooz.repository.utils.Resource
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class DetailMovieViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : DetailMovieViewModel
    private lateinit var service : MovieService
    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)
    private lateinit var observer: Observer<Resource<ReviewMovie>>

    @Before
    fun setUp() {
        service = mockk(relaxed = true)
        observer = mockk(relaxed = true)

        viewModel = DetailMovieViewModel(service, appDispatchers)
    }

    @Test
    @Throws(InterruptedException::class)
    fun `show detail movie and request review movie`(){
        val data = service.getResult(any())

        val fakeReview = Resource.success(FAKE_REVIEW)
        coEvery {
            service.getReviewMovie(any())
        }returns MutableLiveData<Resource<ReviewMovie>>().apply { value =fakeReview }

        viewModel.listReviewMovieData.observeForever(observer)
        viewModel.getModel(any())

        verify {
            observer.onChanged(fakeReview)
        }

        confirmVerified(observer)
        Assert.assertEquals(data, viewModel.model)
    }
}