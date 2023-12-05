package com.example.bostonwhereareu.backendclient;

import com.example.bostonwhereareu.model.dto.PaginatedDTO;
import com.example.bostonwhereareu.model.dto.UserScoreDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaderboardApi {
    @GET("/v1/leaderboard")
    Call<PaginatedDTO<UserScoreDTO>> getLeaderboard(@Query("offset") Integer offset, @Query("limit") Integer limit);
}

