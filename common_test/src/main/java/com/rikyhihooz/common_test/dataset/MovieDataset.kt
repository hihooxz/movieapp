package com.rikyhihooz.common_test.dataset

import com.rikyhihooz.model.MovieModel
import com.rikyhihooz.model.Result
import com.rikyhihooz.model.ResultReview
import com.rikyhihooz.model.ReviewMovie

object MovieDataset {

    val FAKE_MOVIERESULT = listOf(
        Result(1, true, null, null,null, null,
        null, null,null, null,null, null,null),
        Result(1, true, null, null,null, null,
            null, null,null, null,null, null,null))

    val FAKE_MOVIES = MovieModel(1, FAKE_MOVIERESULT, 100, 10)

    val FAKE_REVIEWRESULT = listOf(
        ResultReview("author1",null,null,null),
        ResultReview("author2",null,null,null)
    )

    val FAKE_REVIEW = ReviewMovie(null,null, FAKE_REVIEWRESULT,10, 100)
}