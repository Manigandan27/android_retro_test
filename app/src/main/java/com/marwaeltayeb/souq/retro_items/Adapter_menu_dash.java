package com.marwaeltayeb.souq.retro_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marwaeltayeb.souq.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_menu_dash extends RecyclerView.Adapter<Adapter_menu_dash.ViewHolder> {

    private ArrayList<Dash_menu_model> Dash_items;

    public Adapter_menu_dash(ArrayList<Dash_menu_model> cities) {
        this.Dash_items = cities;
    }


    TextView textView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_dash_menu, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Dash_menu_model dimage = Dash_items.get(position);
        Picasso.get().load(dimage.getUrl()).into(holder.DImage);

        Dash_menu_model dtext = Dash_items.get(position);
        holder.DText.setText(dtext.getName());
        
    }


    @Override
    public int getItemCount() {
        if (Dash_items != null) {
            return Dash_items.size();
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
            DImage = view.findViewById(R.id.dashmenuimg);
            DText=view.findViewById(R.id.dashmenutext);
        }
    }
}




