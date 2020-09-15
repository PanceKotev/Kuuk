package com.mpip.kuuk.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.mpip.kuuk.R;
import com.mpip.kuuk.dto.IngredientDto;

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ingredientViewHolder> {
    List<IngredientDto> dataset;
    private View.OnClickListener mOnClickListener;

    public IngredientListAdapter() {
        this.dataset=new ArrayList<>();
    }

    @NonNull
    @Override
    public ingredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_holder,parent,false);
        return new ingredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientViewHolder holder, int position) {
        holder.setProperties(dataset.get(position).getImgUrl(),dataset.get(position).getName());
    }
    public void setItemListener(View.OnClickListener itemListener){
        this.mOnClickListener=itemListener;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateDataset(List<IngredientDto> newDataset) {
        this.dataset = newDataset;
        notifyDataSetChanged();
    }
    public String getClickedItemName(ingredientViewHolder holder){
        int adapterPosition = holder.getAdapterPosition();
        return dataset.get(adapterPosition).getName();
    }
    public class ingredientViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imageView;
        private MaterialTextView textView;
        public ingredientViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.ingView);
            textView=itemView.findViewById(R.id.ingText);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }

        public void setProperties(String imgUrl,String text){
            textView.setText(text);
            Glide.with(imageView.getContext()).load(imgUrl).centerCrop().circleCrop().error(R.drawable.logout_icon).into(imageView);
        }

        public void setItemSelected(boolean selected){
            if(selected){
                imageView.setColorFilter(Color.argb(0.35f,0,0,0), PorterDuff.Mode.SRC_ATOP);
            }
            else{
                imageView.setColorFilter(Color.argb(0.0f,0,0,0), PorterDuff.Mode.SCREEN);}

        }
    }
}
