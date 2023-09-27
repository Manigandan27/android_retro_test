package com.marwaeltayeb.souq.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marwaeltayeb.souq.R;

import java.util.ArrayList;


public class FlipperAdapter2 extends BaseAdapter {


    private Context mCtx;
    private ArrayList<Banners> heros;

    public FlipperAdapter2(Context mCtx, ArrayList<Banners> heros){
        this.mCtx = mCtx;
        this.heros = heros;
    }
    @Override
    public int getCount() {
        return heros.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Banners banner = heros.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.flipper_items2, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
//        textView.setText(hero.getName());//

        Glide.with(mCtx).load(banner.getUrl()).into(imageView);
        return view;
    }
}
