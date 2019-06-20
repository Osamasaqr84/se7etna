package com.codesroots.osamaomar.sehetna.presentation.details.posts;

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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.Utils.ViewsUtils;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.viewmodel.PostsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateDiff;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateObject;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getdate;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.CustomView> {

    private Context mContext;
    ArrayList<String> images2;
    private MainActivity mainActivity;
    List<Post> postList;
    PostsViewModel postsViewModel;

    public PostsAdapter(List<Post> list, Context context, PostsViewModel postsViewModelC) {
        mContext = context;
        postList = list;
        String imgUrl = "https://images.indianexpress.com/2018/07/thailand-7592.jpg?w=600";
        images2 = new ArrayList<String>();
        images2.add(imgUrl);
        images2.add(imgUrl);
        images2.add(imgUrl);
        images2.add(imgUrl);
        mainActivity = (MainActivity) context;
        postsViewModel = postsViewModelC;
    }

    @NonNull
    @Override
    public PostsAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_item, viewGroup, false);
        return new PostsAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.CustomView customView, int i) {

        customView.name.setText(postList.get(i).getOwner_name());
        if (postList.get(i).getImages() != null && postList.get(i).getImages().size() > 0) {
            customView.post.setVisibility(View.GONE);
            customView.recyclerViewImages.setVisibility(View.VISIBLE);
            ViewsUtils.setupDiffRecycle(customView.recyclerViewImages, postList.get(i).getImages(), mContext);
        }

        if (getDateDiff(getDateObject(postList.get(i).getTime()),
                Calendar.getInstance().getTime(), TimeUnit.HOURS) > 24) {

            customView.time.setText(
                    getdate(postList.get(i).getTime()));
        } else {
            Long hours = getDateDiff(getDateObject(postList.get(i).getTime()),
                    Calendar.getInstance().getTime(), TimeUnit.HOURS);
            if (hours == 0) hours = Long.valueOf(1);

            customView.time.setText(Long.toString(hours) + "h");
        }

        Glide.with(mContext)
                .load(postList.get(i).getOwner_image())
                .fitCenter()
                .into(customView.item_img);

        if (postList.get(i).getImages() != null && postList.get(i).getImages().size() > 0) {
            customView.post.setVisibility(View.GONE);
            customView.recyclerViewImages.setVisibility(View.VISIBLE);
            ViewsUtils.setupDiffRecycle(customView.recyclerViewImages, postList.get(i).getImages(), mContext);
        } else {
            customView.post.setText(postList.get(i).getPostText());
        }

        customView.comment.setOnClickListener(v -> {
            Bundle args = new Bundle();
            if (postList.get(i).getImages() != null && postList.get(i).getImages().size() > 0) {
                args.putStringArrayList("images", new ArrayList<String>(postList.get(i).getImages()));
            } else {
                args.putString("post", postList.get(i).getPostText());
            }
            if (postList.get(i).getReplies() != null) {
                args.putParcelableArrayList("replies", new ArrayList<>(postList.get(i).getReplies()));
            }
            args.putString("name", customView.name.getText().toString());
            args.putString("img", postList.get(i).getOwner_name());
            args.putString("date", customView.time.getText().toString());
            args.putString("like", customView.like.getText().toString());
            args.putString("comment", customView.comment.getText().toString());
            args.putInt("id", Integer.valueOf(postList.get(i).getPostId()));

            ViewCommentsDF newFragment = new ViewCommentsDF();
            newFragment.setArguments(args);
            ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_view, newFragment).commit();
            //  newFragment.show(mainActivity.getSupportFragmentManager(), "TAG");
        });

        customView.like.setOnClickListener(v -> {
            if (PreferenceHelper.getUserId() > 0) {
                if (!postList.get(i).getLiked()) {
                    // customView.like.setEnabled(false);
                    postsViewModel.setCurrentItem(i);
                    postsViewModel.addLike(PreferenceHelper.getUserId(), postList.get(i).getPostId());

                } else {
                    // customView.like.setEnabled(false);
                    postsViewModel.setCurrentItem(i);
                    postsViewModel.removeLike(PreferenceHelper.getUserId(), postList.get(i).getPostId());  // Todo
                }
            } else
                Snackbar.make(customView.mView, "يجب تسجيل الدخول اولا ", Snackbar.LENGTH_SHORT).show();
        });

        customView.like.setText(Integer.toString(postList.get(i).getLikeCount()));
        customView.comment.setText(Integer.toString(postList.get(i).getCommentCount()));

        if (postList.get(i).getLiked()) {
            customView.like.setTextColor(mContext.getResources().getColor(R.color.blue));
            customView.like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.liked, 0);
        } else {
            customView.like.setTextColor(mContext.getResources().getColor(R.color.darkgrey));
            customView.like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.like, 0);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView item_img;
        private TextView comment, distance, price, ratecount, deliverycomment, accept, post, name, time, like;
        RecyclerView recyclerViewImages;

        private CustomView(View view) {
            super(view);
            mView = view;
            comment = mView.findViewById(R.id.comment);
            like = mView.findViewById(R.id.like);
            post = mView.findViewById(R.id.post);
            name = mView.findViewById(R.id.name);
            time = mView.findViewById(R.id.time);
            item_img = mView.findViewById(R.id.item_img);
            recyclerViewImages = mView.findViewById(R.id.post_images);
        }
    }

}
