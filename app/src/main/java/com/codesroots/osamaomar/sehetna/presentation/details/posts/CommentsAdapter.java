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
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateDiff;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateObject;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getdate;

public class CommentsAdapter extends  RecyclerView.Adapter<CommentsAdapter.CustomView> {
Context mContext ;
    ArrayList<CenterPostsResponse.DataBean.PostrepliesBean> replies ;
    public CommentsAdapter( Context context , ArrayList<CenterPostsResponse.DataBean.PostrepliesBean> repliesC) {
        replies  = repliesC ;
        mContext  = context;
    }

    @NonNull
    @Override
    public CommentsAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_item_comment, viewGroup, false);
        return new CommentsAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.CustomView customView, int i) {

        // Todo add  User  to api
        if (replies.get(i).getUser()!=null)  {

            customView.name.setText(replies.get(i).getUser().getUsername());
            Glide.with(mContext)
                .load(replies.get(i).getUser().getPhoto())
                .fitCenter()
                .into(customView.itemImage);
        }

        customView.post.setText(replies.get(i).getReply());

        if (replies.get(i).getCreated()!=null &&replies.get(i).getCreated()!=""){

            if (getDateDiff(getDateObject(replies.get(i).getCreated()) ,
                    Calendar.getInstance().getTime() ,TimeUnit.HOURS)>24){

                customView.time.setText(
                        getdate(replies.get(i).getCreated()));
            }else{
                Long hours = getDateDiff(getDateObject(replies.get(i).getCreated()) ,
                        Calendar.getInstance().getTime() ,TimeUnit.HOURS);
                if (hours ==0) hours = Long.valueOf(1);

                customView.time.setText(Long.toString(hours)+"h" );
            }
    }}

    @Override
    public int getItemCount() {
        return replies.size();
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
