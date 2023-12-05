package com.example.bostonwhereareu.backendclient;

import com.example.bostonwhereareu.model.dto.LoginDTO;
import com.example.bostonwhereareu.model.dto.UserScoreDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationApi {
    @POST("/v1/auth/login")
    Call<UserScoreDTO> loginOrCreateUser(@Body LoginDTO loginDTO);
}

