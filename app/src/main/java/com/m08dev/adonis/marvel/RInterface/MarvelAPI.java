package com.m08dev.adonis.marvel.RInterface;

import com.m08dev.adonis.marvel.json.StoryLM;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;




public interface MarvelAPI {
    @GET("series")
    Call<StoryLM> stories(
            @Query("apikey")String api_key,
            @Query("ts")String ts,
            @Query("hash")String hash,
            @Query("limit")String limit,
            @Query("orderBy")String filter);

}



