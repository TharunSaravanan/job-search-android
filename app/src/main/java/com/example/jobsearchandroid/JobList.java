package com.example.jobsearchandroid;

import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class JobList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        recyclerView = findViewById(R.id.jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Call the API to get the list of jobs
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Job>> call = apiInterface.getAllJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    List<Job> jobList = response.body();
                    adapter = new JobsAdapter(jobList);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Handle API error
                    Toast.makeText(JobList.this, "Failed to fetch jobs.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                // Handle network error
                Toast.makeText(JobList.this, "Network error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}