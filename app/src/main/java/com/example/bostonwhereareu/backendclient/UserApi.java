package com.example.bostonwhereareu.backendclient;

import com.example.bostonwhereareu.model.dto.UserScoreDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @POST("/v1/users/score")
    Call<UserScoreDTO> createOrUpdateUserScore(@Body UserScoreDTO userScoreDTO);

    @GET("/v1/users/name/{name}/score")
    Call<UserScoreDTO> getUserScore(@Path("name") String name);
}

