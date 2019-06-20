package com.codesroots.osamaomar.sehetna.presentation.details.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.R;

import java.util.ArrayList;

public class LikesAdapter extends  RecyclerView.Adapter<LikesAdapter.CustomView> {
Context mContext ;
    ArrayList<CenterPostsResponse.DataBean.PostlikesBean> likes ;
    public LikesAdapter(Context context , ArrayList<CenterPostsResponse.DataBean.PostlikesBean> likesC) {
        likes  = likesC ;
        mContext  = context;
    }

    @NonNull
    @Override
    public LikesAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_item_like, viewGroup, false);

        return new LikesAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesAdapter.CustomView customView, int i) {

        if (likes.get(i).getUser()!=null)  {

            customView.name.setText(likes.get(i).getUser().getUsername());
            Glide.with(mContext)
                .load(likes.get(i).getUser().getPhoto())
                .fitCenter()
                .into(customView.itemImage);
        }

      
       }

    @Override
    public int getItemCount() {
        return likes.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView time , post,name;

        private CustomView(View view) {
            super(view);
            mView = view;
            name = mView.findViewById(R.id.name);
            time = mView.findViewById(R.id.time);
            post = mView.findViewById(R.id.textView2);
            itemImage = mView.findViewById(R.id.img);
        }
    }

}
