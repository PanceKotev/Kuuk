package com.mpip.kuuk.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.mpip.kuuk.R;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.dto.RecipeDto;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private List<RecipeDto> dataset;
    private View.OnClickListener mOnClickListener;

    public RecipeListAdapter() {
        this.dataset=new ArrayList<>();
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_holder,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeDto recipe=dataset.get(position);
        holder.setProperties(recipe.getName(),recipe.getCookingTime()+"",recipe.getImgUrl());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateDataset(List<RecipeDto> newDataset){
        this.dataset=newDataset;
        notifyDataSetChanged();
    }

    public void setItemListener(View.OnClickListener itemListener){
        this.mOnClickListener=itemListener;
    }

    public RecipeDto getClickedItemName(RecipeListAdapter.RecipeViewHolder holder){
        int adapterPosition = holder.getAdapterPosition();
        return dataset.get(adapterPosition);
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView recName;
        private MaterialTextView cookTime;
        private ImageView cardImage;
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recName = itemView.findViewById(R.id.rec_name);
            cookTime = itemView.findViewById(R.id.cookTimeTV);
            cardImage = itemView.findViewById(R.id.cardImage);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }
        public void setProperties(String recipeName,String cookingTime, String imgUrl){
            recName.setText(recipeName);
            cookTime.setText(cookingTime+"min");
            Glide.with(cardImage.getContext()).load(imgUrl).centerCrop().error(R.drawable.logout_icon).into(cardImage);
            cardImage.setColorFilter(Color.argb(0.15f,0,0,0), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
