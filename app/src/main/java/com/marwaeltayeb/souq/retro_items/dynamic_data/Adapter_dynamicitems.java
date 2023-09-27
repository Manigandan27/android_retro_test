package com.marwaeltayeb.souq.retro_items.dynamic_data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.marwaeltayeb.souq.R;

import java.util.ArrayList;


public class Adapter_dynamicitems extends RecyclerView.Adapter<Adapter_dynamicitems.ViewHolder> {


    private ArrayList<Datum> DPimages;

    private ArrayList<DashboardProductList> Dimages;

    Adapter_ditems adapter_ditems;

    private static final String TAG = "ProductActivity";
    Context context;

    public Adapter_dynamicitems(ArrayList<Datum> cities) {
        this.DPimages = cities;
        this.context = context;

    }

    //    public Adapter_dynamicitems(ArrayList<DashboardProductList> cities) {
//        this.Dimages = cities;
//
//    }
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;


//
//    private MyListData[] listdata;
//    private MyListAdapter2 adapter;
//    Context context;
//    LinearLayoutManager linearLayoutManager;

    // RecyclerView recyclerView;
//    public Adapter_dynamicitems(MyListData[] listdata, Context context) {
//        this.context = context;
//        this.listdata = listdata;
//    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem = layoutInflater.inflate(R.layout.recyle_dynamic_design, parent, false);
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        ArrayList<DashboardProductList> dashboardproduct = DPimages.get(position).getDashboardProductLists();

        Gson gson = new Gson();
        String json1 = gson.toJson(dashboardproduct);
        Log.d(TAG, " qqqqqq : " + json1);

        adapter_ditems = new Adapter_ditems(dashboardproduct);
        holder.recyclerView.setHasFixedSize(true);

// linearLayoutManager=new GridLayoutManager(context.getApplicationContext(), 4);
//        holder.recyclerView.setLayoutManager(linearLayoutManager);
//        holder.recyclerView.addItemDecoration(new DividerItemDecoration(context.getApplicationContext(), LinearLayoutManager.HORIZONTAL));

//        gridLayoutManager=new GridLayoutManager(context, 4);
//        holder.recyclerView.setLayoutManager(gridLayoutManager);


        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        holder.recyclerView.setAdapter(adapter_ditems);


//        final MyListData myListData = listdata[position];
//        DashboardProductList dimage = DPimages.get(position);


//        Datum dimage1 = DPimages.get(position);
//        dimage1.getDashboardProductLists();

//        GridLayoutManager DLayoutManager=new GridLayoutManager(context, 4);
//        holder.recyclerView.setLayoutManager(DLayoutManager);
//        holder.recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL));
//        holder.recyclerView.setAdapter(adapter_ditems);

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
        public TextView Dname, Ddesk;

        RecyclerView recyclerView;
        GridLayoutManager DLayoutManage;

        RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            recyclerView = view.findViewById(R.id.recyclerView);
//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);


//            Dname =view.findViewById(R.id.dynamictitlename);

//            DImage = view.findViewById(R.id.retrodynamicimg);
//            Dname = view.findViewById(R.id.retroproductname);
//            Ddesk = view.findViewById(R.id.retroproductdesc);

        }
    }
}

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        //        public ImageView imageView;
////        public TextView textView;
//        public RelativeLayout relativeLayout;
//
//        RecyclerView recyclerView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//       /*     this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.textView = (TextView) itemView.findViewById(R.id.textView);
//       */
//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);

//        }
//    }
//}

   /* private ArrayList<Datum> DPimages;

    private static final String TAG = "ProductActivity";
    Context context;

    public Adapter_dynamicitems(ArrayList<Datum> cities) {
        this.DPimages = cities;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_dynamic_design, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        DashboardProductList dimage = DPimages.get(position);
//        DashboardProductList dimage = DPimages.get(position);
//        Picasso.get().load(dimage.getUrl()).into(holder.DImage);//img

//        ArrayList singleSectionItems = DPimages.get(position).getDashboardProductLists();

//        ArrayList<DashboardProductList> singleSectionItems = DPimages.get(position).getDashboardProductLists();

        Datum dname = DPimages.get(position);//name
//        holder.Dname.setText((CharSequence) dname.getDashboardProductLists());

//        ArrayList<DashboardProductList> dashboardproduct = DPimages.get(position).getDashboardProductLists();
//        Adapter_ditems adapter_ditems =new Adapter_ditems(dashboardproduct);

        Datum dimage1=DPimages.get(position);
        dimage1.getDashboardProductLists();

//        Adapter_ditems adapter_ditems =new Adapter_ditems(dimage1);

        Gson gson = new Gson();
        String json1 = gson.toJson(dimage1);
        Log.d(TAG, " qqqqqq : " + json1);

        holder.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager DLayoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(DLayoutManager);
//        holder.recyclerView.setAdapter(dimage1);


//        Datum dimage1=DPimages.get(position);
//        dimage1.getDashboardProductLists();




//        Adapter_ditems itemListDataAdapter = new Adapter_ditems(dimage1);
////
//        holder.recyclerView.setHasFixedSize(true);
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager( context,LinearLayoutManager.VERTICAL, false));
//        holder.recyclerView.setAdapter(itemListDataAdapter);



//        Datum dimage1=DPimages.get(position);
//        dimage1.getDashboardProductLists();
//
//        Gson gson = new Gson();
//        String json1 = gson.toJson(DPimages);
//        Log.d(TAG, " qqqqqq : " + json1);

//        Product_model_1 dname = DPimages.get(position);//name
//        holder.Dname.setText(dname.getName());
//
//        Product_model_1 ddesk = DPimages.get(position);
//        holder.Ddesk.setText(ddesk.getDesc());

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

        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            recyclerView =view.findViewById(R.id.recycle_dynamic_items);
//            Dname =view.findViewById(R.id.dynamictitlename);

//            DImage = view.findViewById(R.id.retrodynamicimg);
//            Dname = view.findViewById(R.id.retroproductname);
//            Ddesk = view.findViewById(R.id.retroproductdesc);

        }
    }

    */





