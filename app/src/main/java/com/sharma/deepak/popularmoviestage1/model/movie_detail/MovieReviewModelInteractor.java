package com.sharma.deepak.popularmoviestage1.model.movie_detail;

public interface MovieReviewModelInteractor {

    void callReviewWebAPI(String movieID, String languageCode, int pageNumber);
}
