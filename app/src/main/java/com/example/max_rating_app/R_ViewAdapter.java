package com.example.max_rating_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class R_ViewAdapter extends RecyclerView.Adapter<R_ViewAdapter.RVAHolder> {

    String s1[], s2[], image_main_url[],image_logo_url[];
    Context context;

    public R_ViewAdapter(Context context, String[] s1, String[] s2,String image_main_url[],String image_logo_url[]) {
        this.s1 = s1;
        this.s2 = s2;
        this.context = context;
        this.image_main_url = image_main_url;
        this.image_logo_url = image_logo_url;
    }

    @NonNull
    @Override
    public RVAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_row , parent, false);
        return new RVAHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAHolder holder, int position) {
        holder.header.setText(s1[position]);
        holder.type.setText(s2[position]);
        holder.setLogoImageView(image_logo_url[position]);
        holder.setMainImageView(image_main_url[position]);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class RVAHolder extends RecyclerView.ViewHolder {
        TextView header, type;
        ImageView main, logo;
        public RVAHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header_name);
            type = itemView.findViewById((R.id.type));
        }
        // method for load image from url

        public void setMainImageView(String url){
            main = itemView.findViewById(R.id.main_image);
            Glide.with(context).load(url).into(main);
        }
        public void setLogoImageView(String url){
            logo = itemView.findViewById(R.id.logo);
            Glide.with(context).load(url).into(logo);
        }

    }
}
