package com.example.restapi.api;

import com.example.restapi.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Photos {
//    definir o endpoint
    @GET("/v2/list?limit=10")
    Call<List<Photo>> getAllPhotos();

    @GET("/v2/list")
    Call<List<Photo>> getPhoto(@Query("limit") int limit);
}
