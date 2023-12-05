package com.example.bostonwhereareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.bostonwhereareu.backendclient.AuthenticationApi;
import com.example.bostonwhereareu.backendclient.LeaderboardApi;
import com.example.bostonwhereareu.backendclient.UserApi;
import com.example.bostonwhereareu.model.dto.LoginDTO;
import com.example.bostonwhereareu.model.dto.PaginatedDTO;
import com.example.bostonwhereareu.model.dto.UserScoreDTO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendApiTest {
    private MockWebServer mockWebServer;
    private Retrofit retrofit;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Test
    public void testLoginOrCreateUser() throws Exception {
        // Mock response
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"userName\":\"testUser\", \"score\":100}")
                .addHeader("Content-Type", "application/json"));

        // API call
        AuthenticationApi service = retrofit.create(AuthenticationApi.class);
        Response<UserScoreDTO> response = service.loginOrCreateUser(new LoginDTO("testUser")).execute();

        // Assertions
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals("testUser", response.body().getUserName());
        assertEquals(100, response.body().getScore());
    }

    @Test
    public void testGetLeaderboard() throws Exception {
        // Mock response for leaderboard
        String leaderboardJson = "{\"totalCount\":1, \"offset\":0, \"limit\":10, \"items\":[{\"userName\":\"user1\", \"score\":100}]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(leaderboardJson)
                .addHeader("Content-Type", "application/json"));

        // API call
        LeaderboardApi service = retrofit.create(LeaderboardApi.class);
        Response<PaginatedDTO<UserScoreDTO>> response = service.getLeaderboard(0, 10).execute();

        // Assertions
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals(1, response.body().getTotalCount());
        assertEquals(1, response.body().getItems().size());
        assertEquals("user1", response.body().getItems().get(0).getUserName());
        assertEquals(100, response.body().getItems().get(0).getScore());
    }

    @Test
    public void testCreateOrUpdateUserScore() throws Exception {
        // Mock response for user score update
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"userName\":\"user1\", \"score\":150}")
                .addHeader("Content-Type", "application/json"));

        // API call
        UserApi service = retrofit.create(UserApi.class);
        UserScoreDTO userScoreDTO = new UserScoreDTO("user1", 150);
        Response<UserScoreDTO> response = service.createOrUpdateUserScore(userScoreDTO).execute();

        // Assertions
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals("user1", response.body().getUserName());
        assertEquals(150, response.body().getScore());
    }

    @Test
    public void testGetUserScore() throws Exception {
        // Mock response for getting user score
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"userName\":\"user2\", \"score\":200}")
                .addHeader("Content-Type", "application/json"));

        // API call
        UserApi service = retrofit.create(UserApi.class);
        Response<UserScoreDTO> response = service.getUserScore("user2").execute();

        // Assertions
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertEquals("user2", response.body().getUserName());
        assertEquals(200, response.body().getScore());
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
