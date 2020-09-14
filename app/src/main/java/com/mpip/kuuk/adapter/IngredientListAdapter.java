package com.mpip.kuuk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpip.kuuk.R;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.viewholder.ingredientViewHolder;

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter {
    List<IngredientDto> dataset;
    View.OnClickListener listener;

    public IngredientListAdapter(View.OnClickListener listener) {
        this.dataset=new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_holder,parent,false);
        return new ingredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ingredientViewHolder)holder).setProperties(dataset.get(position).getImgUrl(),dataset.get(position).getName(),listener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void updateDataset(List<IngredientDto> newDataset) {
        this.dataset = newDataset;
        notifyDataSetChanged();
    }
    public Long getClickedItemId(ingredientViewHolder holder){
        int adapterPosition = holder.getAdapterPosition();
        return dataset.get(adapterPosition).getIngredientID();
    }

}
