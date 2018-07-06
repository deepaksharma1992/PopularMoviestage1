package com.sharma.deepak.popularmoviestage1.model.movie_detail_module;

public interface SimilarMovieModelInteractor {
    void callSimilarMovieWebAPI(String movieId, String languageCode, int pageNumber);
}
