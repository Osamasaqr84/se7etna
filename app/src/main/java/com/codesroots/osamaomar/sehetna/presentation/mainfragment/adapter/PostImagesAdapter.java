package com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.entities.ZColor;

import java.util.ArrayList;
import java.util.List;


public class PostImagesAdapter extends RecyclerView.Adapter<PostImagesAdapter.ViewHolder>  {

    private Context context;
    int width;
    ArrayList<String> imagesList;
    public  PostImagesAdapter(List<String> images , Context mcontext) {
        context = mcontext;
        imagesList = new ArrayList<String>(images)  ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_img, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {


        holder.mView.setOnClickListener(v-> {
            ZGallery.with(( (MainActivity)(v.getContext())) , imagesList)
                    .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                    .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                    .setToolbarColorResId(R.color.colorPrimary) // toolbar color
                    .setTitle("Gallery") // toolbar title
                    .show();

        });

        if (position>=3){
            holder.Image.setVisibility(View.GONE);
            return;}

        Glide.with(context)
                .load(imagesList.get(position))
                .into(holder.Image);


        width = holder.mView.getWidth();
        if( imagesList.size()==1){
            ViewGroup.LayoutParams layoutParams = holder.Image.getLayoutParams();
            layoutParams.height=layoutParams.height*2+10;
            layoutParams.width= (int) (layoutParams.width*2);
            holder.Image.setLayoutParams(layoutParams);
        }
        else if (imagesList.size()==2){
            ViewGroup.LayoutParams layoutParams = holder.Image.getLayoutParams();
           layoutParams.height=layoutParams.height/**2+10*/;
            layoutParams.width= (int) (layoutParams.width*1.2);
            holder.Image.setLayoutParams(layoutParams);
        }
        else if (imagesList.size()==3){
            if (position==0)
            {
                ViewGroup.LayoutParams layoutParams = holder.Image.getLayoutParams();
                layoutParams.height=layoutParams.height*2+10;
                layoutParams.width= (int) (layoutParams.width*1.5);
                holder.Image.setLayoutParams(layoutParams);
            }
        }
        else if (imagesList.size()>3){
            if (position==0)
            {
                ViewGroup.LayoutParams layoutParams = holder.Image.getLayoutParams();
                layoutParams.height=layoutParams.height*2+10;
                layoutParams.width= (int) (layoutParams.width*1.5);
                holder.Image.setLayoutParams(layoutParams);
            }
            if (position==2){
                holder.images_num.setVisibility(View.VISIBLE );
                holder.images_num.setText("+"+Integer.toString(imagesList.size()-3) );
            }
        }

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private ImageView Image;
        private TextView images_num;

        ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.item_img);
            images_num = mView.findViewById(R.id.images_num);
        }
    }
}