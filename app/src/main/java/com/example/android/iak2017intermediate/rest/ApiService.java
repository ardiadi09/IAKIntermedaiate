package com.example.android.iak2017intermediate.rest;

import com.example.android.iak2017intermediate.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("articles")
    Call<APIResponse> getArticles(
            @Query("source") String source,
            @Query("apikey") String apiKey
    );
}
