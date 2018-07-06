package com.sharma.deepak.popularmoviestage1.model.movie_detail;

public interface SimilarMovieModelInteractor {
    void callSimilarMovieWebAPI(String movieId, String languageCode, int pageNumber);
}
