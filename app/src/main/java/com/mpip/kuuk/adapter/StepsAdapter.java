package com.mpip.kuuk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.mpip.kuuk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    Map<String,String> dataset;

    public StepsAdapter() {
        dataset=new LinkedHashMap<String,String>();
    }


    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.step_holder,parent,false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        String stepNmbr1=(new ArrayList<String>(dataset.keySet())).get(position);
        String stepDescription1=(new ArrayList<String>(dataset.values())).get(position);
        holder.setProperties(stepNmbr1,stepDescription1);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void updateDataset(Map<String,String> steppies){
        this.dataset=steppies;
        notifyDataSetChanged();
    }
    public class StepsViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView stepNmbr;
        private MaterialTextView stepDescript;
        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNmbr=itemView.findViewById(R.id.stepNumberTV);
            stepDescript=itemView.findViewById(R.id.stepDescription);
        }
        public void setProperties(String stepNumber, String stepDescription){
            stepNmbr.setText(stepNumber+".");
            stepDescript.setText(stepDescription);
        }
    }
}
