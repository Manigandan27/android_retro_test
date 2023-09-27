package com.marwaeltayeb.souq.retro_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.recycle.DashboardAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_retrodash extends RecyclerView.Adapter<Adapter_retrodash.ViewHolder> {

    private ArrayList<Dashboard_model_1> DRimages;

    public Adapter_retrodash(ArrayList<Dashboard_model_1> cities) {
        this.DRimages = cities;
    }


    TextView textView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_dashboard_design, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Dashboard_model_1 dimage = DRimages.get(position);
        Picasso.get().load(dimage.getUrl()).into(holder.DImage);

        Dashboard_model_1 dtext = DRimages.get(position);
        holder.DText.setText(dtext.getName());


    }

    @Override
    public int getItemCount() {
        if (DRimages != null) {
            return DRimages.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        public ImageView DImage;
        public TextView DText;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            DImage = view.findViewById(R.id.retrodashimg);
            DText=view.findViewById(R.id.retrodashtext);
        }
    }
}




