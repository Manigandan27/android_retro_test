package com.marwaeltayeb.souq.retro_items;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.recycle.Banners;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Adapter_Slider extends SliderViewAdapter<Adapter_Slider.SliderAdapterVH> {



    private List<SliderModelClass> mSliderItems ;
    private Context context;

    public Adapter_Slider(Context applicationContext, ArrayList<SliderModelClass> cities) {
        this.mSliderItems = cities;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderModelClass sliderItem = mSliderItems.get(position);


        Picasso.get().load(sliderItem.getUrl()).into(viewHolder.imageViewBackground);//img
        Picasso.get().load(sliderItem.getUrl()).into(viewHolder.imageGifContainer);//img


    }

    @Override
    public int getCount() {
        if (mSliderItems != null) {
            return mSliderItems.size();
        } else {
            return 0;
        }
//        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            this.itemView = itemView;

            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);


        }
    }

}



