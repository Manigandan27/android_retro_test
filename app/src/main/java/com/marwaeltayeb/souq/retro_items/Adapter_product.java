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

public class Adapter_product extends RecyclerView.Adapter<Adapter_product.ViewHolder> {

    private ArrayList<Product_model_1> DPimages;

    public Adapter_product(ArrayList<Product_model_1> cities) {
        this.DPimages = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_product_design, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product_model_1 dimage = DPimages.get(position);
        Picasso.get().load(dimage.getUrl()).into(holder.DImage);//img

        Product_model_1 dname = DPimages.get(position);//name
        holder.Dname.setText(dname.getName());

        Product_model_1 ddesk = DPimages.get(position);
        holder.Ddesk.setText(ddesk.getDesc());


    }

    @Override
    public int getItemCount() {
        if (DPimages != null) {
            return DPimages.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        public ImageView DImage;
        public TextView Dname,Ddesk;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            DImage = view.findViewById(R.id.retroproductimg);
            Dname = view.findViewById(R.id.retroproductname);
            Ddesk = view.findViewById(R.id.retroproductdesc);

        }
    }
}




