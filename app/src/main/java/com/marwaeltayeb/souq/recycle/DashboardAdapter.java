package com.marwaeltayeb.souq.recycle;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.marwaeltayeb.souq.R;

import java.util.ArrayList;
import java.util.List;

//public class DashboardAdapter extends RecyclerView.Adapter<com.inihood.loginandregistermvvm.adapter.DashboardAdapter.MyViewHolder> {

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder>{



    List<ToDoModel> data ;
    Context context;

    ArrayList<ToDoModel> models;
    private List<ToDoModel>itemList;

    public DashboardAdapter(Context context, ArrayList<ToDoModel>data) {
        this.context = context;
        this.data = data;
    }

//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//
//        public ImageView imageView;
//
//        public MyViewHolder(View v){
//            super(v);
//
//
//            imageView =  v.findViewById(R.id.icon);
//
//        }
//    }
//
//    @Override
//    public DashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_all_mobiles, parent, false);
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position){
//
//        ToDoModel row=itemList.get(position);
//
//        holder.imageView.setImageResource(row.getImageId());
//
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return data.size();
//    }
//

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;

        public ImageView Icon;
        private LinearLayout main;

        public MyViewHolder(final View parent) {
            super(parent);
//            title = (TextView) parent.findViewById(R.id.title);

            Icon =  parent.findViewById(R.id.icon);//imageview
            main = parent.findViewById(R.id.ll_item);
//           main = parent.findViewById(R.id.listOfMobiles);

        }
    }
    public DashboardAdapter(List<ToDoModel>itemList){
        this.itemList=itemList;
    }
    @Override
    public DashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_dashboard_design,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ToDoModel row=itemList.get(position);

        holder.Icon.setImageResource(row.getImageId());
    }
    

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

/* Context context;
    ArrayList<ToDoModel> models;

    private List<ToDoModel> listUsers;//

    View view;


    private List<ToDoModel>itemList;



    @Override
    public DashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        view = inflater.from(parent.getContext()).inflate(R.layout.recyle_dashboard_design, parent, false);
        MyViewHolder rvViewHolder = new MyViewHolder(view);
        return rvViewHolder;
    }


    public DashboardAdapter(List<ToDoModel>model){
        this.models=model;
    }

    @Override
    public void onBindViewHolder(DashboardAdapter.MyViewHolder holder, final int position) {
        final ToDoModel model = models.get(position);

        holder.icon.setImageResource(model.getImageId());

//        holder.removeImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                models.remove(position);
//                notifyDataSetChanged();
//            }
//        });
//-------------------
//        holder.removeImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                models.remove(position);
//                notifyDataSetChanged();
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return models.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,itemName2;
        ImageView removeImg,item3;
        LinearLayout llItem;

        public ImageView icon;
        private LinearLayout main;

        public MyViewHolder(View itemView) {
            super(itemView);
//            itemName = itemView.findViewById(R.id.tv_name);
//            itemName2 = itemView.findViewById(R.id.tv_name2); //new

//            item3=itemView.findViewById(R.id.rviewimg);

//            removeImg = itemView.findViewById(R.id.rviewimg);
//            llItem = itemView.findViewById(R.id.ll_item);

            icon = (ImageView) itemView.findViewById(R.id.rviewimg);
            main = (LinearLayout) itemView.findViewById(R.id.ll_item);


        }
    }*/

    /*
    List<Data_ApiModel> data ;
Context context;

public RecyclerViewAdapter(Context context, ArrayList<Data_ApiModel>data) {
    this.context = context;
    this.data = data;
}

     */