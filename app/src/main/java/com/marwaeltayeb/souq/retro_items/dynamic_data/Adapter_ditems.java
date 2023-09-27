package com.marwaeltayeb.souq.retro_items.dynamic_data;

import android.content.Context;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.retro_items.Product_model_1;
import com.marwaeltayeb.souq.view.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter_ditems extends RecyclerView.Adapter<Adapter_ditems.ViewHolder> {



    private ArrayList<DashboardProductList> DPimages;

    Context context;
    private static final String TAG = "ProductActivity";

    public Adapter_ditems(ArrayList<DashboardProductList> cities) {
        this.DPimages = cities;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recyle_dynamic_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.linear_Title.setVisibility(View.GONE);
        if(position==0){
            holder.linear_Title.setVisibility(View.VISIBLE);
        }


        DashboardProductList dimage = DPimages.get(position);
        Picasso.get().load(dimage.getUrl()).into(holder.imageView);//img

        DashboardProductList dname = DPimages.get(position);//name
        holder.Dname.setText(dname.getName());

        DashboardProductList dtitle = DPimages.get(position);//name
        holder.Dtitle.setText(dtitle.getTitle());

        DashboardProductList ddesc = DPimages.get(position);//name
        holder.Ddesc.setText(ddesc.getDesc());


        Gson gson = new Gson();
        String json1 = gson.toJson(dimage);
        Log.d(TAG, " qqqqqq : " + json1);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  //  Toast.makeText( context  , "Clicked Position"+dtitle.getTitle(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Data nnnnn : "+ dname.getName());
            }
        });
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
        public ImageView imageView;
        public TextView Dname,Dtitle,Ddesc;

        public RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        LinearLayout linear_Title;


        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.retrodynamicimg);
            this.Dname = (TextView) itemView.findViewById(R.id.retrodynamicname);
            this.Dtitle = (TextView) itemView.findViewById(R.id.texttitle);
            this.Ddesc=itemView.findViewById(R.id.retrodynamicdesc);

//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            linearLayout = itemView.findViewById(R.id.linear_item);
            linear_Title=itemView.findViewById(R.id.linear_title);
        }
    }
}







