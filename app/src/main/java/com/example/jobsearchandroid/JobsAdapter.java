package com.example.jobsearchandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private List<Job> jobList;

    public JobsAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item_layout, parent, false);
        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.titleTextView.setText(job.getName());
        holder.descriptionTextView.setText(job.getDescription());
        holder.locationTextView.setText(job.getState());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView locationTextView;

        JobViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
        }
    }
}

