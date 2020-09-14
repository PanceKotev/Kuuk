package com.mpip.kuuk.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.mpip.kuuk.R;

public class ingredientViewHolder extends RecyclerView.ViewHolder {
    private ShapeableImageView imageView;
    private MaterialTextView textView;
    public ingredientViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.ingView);
        textView=itemView.findViewById(R.id.ingText);
        itemView.setTag(this);
    }

    public void setProperties(String imgUrl,String text,View.OnClickListener listener){
        textView.setText(text);
        Glide.with(imageView.getContext()).load(imgUrl).centerCrop().error(R.drawable.logout_icon).into(imageView);
        imageView.setOnClickListener(listener);
    }
}
