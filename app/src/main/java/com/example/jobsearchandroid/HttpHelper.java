package com.example.jobsearchandroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {
    private static final String TAG = HttpHelper.class.getSimpleName();
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().create();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");



    public static String login(String userName, String password) throws IOException {
        Request request = new Request.Builder()
                .url(GlobalContainer.ServerApiUrl + "User/" + userName + "/" + password)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            return response.body().string();
        }
    }

    public static String register(User user) throws IOException {
        String jsonPayload = gson.toJson(user);
        RequestBody requestBody = RequestBody.create(JSON, jsonPayload);

        Request request = new Request.Builder()
                .url(GlobalContainer.ServerApiUrl + "User")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            return response.body().string();
        }
    }
}
