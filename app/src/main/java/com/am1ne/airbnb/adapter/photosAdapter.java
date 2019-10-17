package com.am1ne.airbnb.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.am1ne.airbnb.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class photosAdapter extends RecyclerView.Adapter<photosAdapter.ViewHolder> {

    Context context;
    ArrayList<Integer> photo_urls;
    LayoutInflater layoutInflater;


    public photosAdapter(Context context, ArrayList<Integer> photo_urls) {
        this.context = context;
        this.photo_urls = photo_urls;
        Log.d("tagini", "photosAdapter: ");
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.photo_item, null, false);
        Log.d("tagini", "onCreateViewHolder: "+this.photo_urls.size());

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("tagini", "onBindViewHolder: "+this.photo_urls.size());
        Glide.with(context)
                .load(photo_urls.get(position))
                .into(holder.img_photo);

        holder.rl_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_photo, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView alert_img_photo = dialogView.findViewById(R.id.alert_img_photo);
                Glide.with(context)
                        .load(photo_urls.get(position))
                        .into(alert_img_photo);
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return photo_urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_photo;
        RelativeLayout rl_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_photo = itemView.findViewById(R.id.img_photo);
            rl_photo = itemView.findViewById(R.id.rl_photo);
            Log.d("tagini", "ViewHolder: ");
            itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT));

        }
    }
}
