package com.codesroots.osamaomar.sehetna.presentation.details.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.PhotosResponse;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.entities.ZColor;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends  RecyclerView.Adapter<PhotosAdapter.CustomView> {

    private final MainActivity mainActivity;
    private final List<PhotosResponse.DataBean.HcphotosBean> postList;
    private Context mContext;
    List<String> images;
    public PhotosAdapter(List<PhotosResponse.DataBean.HcphotosBean> list, Context context ) {
        mContext  = context;
        postList = list;
        mainActivity = (MainActivity) context;

        images =  Stream.of(list)
                .map(PhotosResponse.DataBean.HcphotosBean::getPhoto)
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    public PhotosAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_item, viewGroup, false);

        return new PhotosAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.CustomView customView, int i) {

        Glide.with(mContext)
                .load(postList.get(i).getPhoto())
                .into(customView.itemImage);
        customView.mView.setOnClickListener(v->{
            ZGallery.with(( (MainActivity)(v.getContext()) )  ,    new ArrayList<String>(images))
                    .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                    .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                    .setToolbarColorResId(R.color.colorPrimary) // toolbar color
                    .setTitle("Gallery") // toolbar title
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;


        private CustomView(View view) {
            super(view);
            mView = view;
            itemImage  = mView.findViewById(R.id.img);

        }
    }

}
