package com.example.restapi.api;

import com.example.restapi.Feeds;
import com.example.restapi.model.Feed;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FeedApi {
    @POST("/posts")
    Call<Feed> salvarFeed(@Body Feed feeds);

}
