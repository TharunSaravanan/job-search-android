package com.example.jobsearchandroid;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("internship/getAll")
    Call<List<Job>> getAllJobs();
}
