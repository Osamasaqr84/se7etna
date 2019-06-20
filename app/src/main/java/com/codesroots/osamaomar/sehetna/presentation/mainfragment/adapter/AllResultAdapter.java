package com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.Utils.ViewsUtils;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.ViewCommentsDF;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.ViewLikesDF;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateDiff;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateObject;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getdate;

public class AllResultAdapter extends RecyclerView.Adapter<AllResultAdapter.ViewHolder> {

    private Context mContext;
    private MainActivity mainActivity;
    List<Post> postList;
    MainViewModel mainViewModel;

    public AllResultAdapter(List<Post> list, Context context, MainViewModel mainViewModelc) {
        mContext = context;
        postList = list;
        mainViewModel = mainViewModelc;
        mainActivity = (MainActivity) context;
    }

    @Override
    public AllResultAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);
        return new AllResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (postList.get(i) != null) {
            viewHolder.name.setText(postList.get(i).getOwner_name());
            viewHolder.ratingBar.setRating(postList.get(i).getRating());
        }
        viewHolder.mView.setOnClickListener(v -> {
            mainViewModel.getGotoDetails().postValue(postList.get(i).getSearchEntity());
        });

        viewHolder.rateCount.setText("(" + Integer.toString(postList.get(i).getRateCount()) + ")");

        if (getDateDiff(getDateObject(postList.get(i).getTime()),
                Calendar.getInstance().getTime(), TimeUnit.HOURS) > 24) {

            viewHolder.time.setText(
                    getdate(postList.get(i).getTime()));
        } else {
            Long hours = getDateDiff(getDateObject(postList.get(i).getTime()),
                    Calendar.getInstance().getTime(), TimeUnit.HOURS);
            if (hours == 0) hours = Long.valueOf(1);

            viewHolder.time.setText(Long.toString(hours) + "h");
        }

        Glide.with(mContext)
                .load(postList.get(i).getOwner_image())
                .fitCenter()
                .into(viewHolder.post_img);

        if (postList.get(i).getImages() != null && postList.get(i).getImages().size() > 0) {
            viewHolder.postText.setVisibility(View.GONE);
            viewHolder.recyclerViewImages.setVisibility(View.VISIBLE);
            ViewsUtils.setupDiffRecycle(viewHolder.recyclerViewImages, postList.get(i).getImages(), mContext);
        } else {
            viewHolder.postText.setText(postList.get(i).getPostText());
        }

        viewHolder.comment.setOnClickListener(v -> {
            Bundle args = new Bundle();
            if (postList.get(i).getImages() != null && postList.get(i).getImages().size() > 0) {
                args.putStringArrayList("images", new ArrayList<String>(postList.get(i).getImages()));
            } else {
                args.putString("post", postList.get(i).getPostText());
            }
            if (postList.get(i).getReplies() != null) {

                args.putParcelableArrayList("replies", new ArrayList<CenterPostsResponse.DataBean.PostrepliesBean>(postList.get(i).getReplies()));
            }
            args.putString("name", viewHolder.name.getText().toString());
            args.putString("img", postList.get(i).getOwner_name());
            args.putString("date", viewHolder.time.getText().toString());
            args.putString("like", viewHolder.like.getText().toString());
            args.putString("comment", viewHolder.comment.getText().toString());
            args.putInt("id", Integer.valueOf(postList.get(i).getPostId()));
            args.putBoolean("islike", postList.get(i).getLiked());
            //  args.putInt("id", Integer.valueOf(postList.get(i).getPostId()));

            ViewCommentsDF newFragment = new ViewCommentsDF();
            newFragment.setArguments(args);
            ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_view, newFragment).commit();
            // newFragment.show(mainActivity.getSupportFragmentManager(), "TAG");
        });

        viewHolder.like.setOnClickListener(v -> {
            if (PreferenceHelper.getUserId() > 0) {
                if (!postList.get(i).getLiked()) {
                    mainViewModel.setCurrentItem(i);
                    mainViewModel.addLike(PreferenceHelper.getUserId(), postList.get(i).getPostId());
                } else {
                    mainViewModel.setCurrentItem(i);
                    mainViewModel.removeLike(PreferenceHelper.getUserId(), postList.get(i).getPostId());  // Todo
                }
            } else
                Snackbar.make(viewHolder.mView, "يجب تسجيل الدخول اولا ", Snackbar.LENGTH_SHORT).show();
        });

        viewHolder.like.setOnLongClickListener(v -> {
            if (postList.get(i).getPostlikesBeanList() != null && postList.get(i).getPostlikesBeanList().size() > 0) {
                Bundle args = new Bundle();
                args.putParcelableArrayList("likes", new ArrayList<CenterPostsResponse.DataBean.PostlikesBean>(postList.get(i).getPostlikesBeanList()));
                ViewLikesDF newFragment = new ViewLikesDF();
                newFragment.setArguments(args);
                newFragment.show(mainActivity.getSupportFragmentManager(), "TAG");
            }
            return false;
        });
        viewHolder.comment.setText(Integer.toString(postList.get(i).getCommentCount()));
        viewHolder.like.setText(Integer.toString(postList.get(i).getLikeCount()));

        if (postList.get(i).getLiked()) {
            viewHolder.like.setTextColor(mContext.getResources().getColor(R.color.blue));
            viewHolder.like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.liked, 0);
        } else {
            viewHolder.like.setTextColor(mContext.getResources().getColor(R.color.darkgrey));
            viewHolder.like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.like, 0);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView name, postText, time, rateCount, like, comment;
        RatingBar ratingBar;
        ImageView post_img;
        RecyclerView recyclerViewImages;

        public ViewHolder(View v) {
            super(v);
            mView = v;

            name = mView.findViewById(R.id.name);
            like = mView.findViewById(R.id.like);
            comment = mView.findViewById(R.id.comment);
            rateCount = mView.findViewById(R.id.rate_count);
            postText = mView.findViewById(R.id.postText);
            time = mView.findViewById(R.id.time);
            ratingBar = mView.findViewById(R.id.rating);
            post_img = mView.findViewById(R.id.img);
            recyclerViewImages = mView.findViewById(R.id.post_images);
        }
    }

}
