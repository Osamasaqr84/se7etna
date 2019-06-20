package com.codesroots.osamaomar.sehetna.presentation.details.posts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.Entities.PostRepliesResponse;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.Utils.ViewsUtils;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.viewmodel.PostsViewModel;

import java.util.ArrayList;

public class ViewCommentsDF extends Fragment {

    RecyclerView comments_recycle;
    EditText addReply;
    TextView post, tvAddFirst, name, time, like, comment, tvAddComment;
    ArrayList<CenterPostsResponse.DataBean.PostrepliesBean> replyList;
    private CommentsAdapter adapter;
    ImageView img;
    RecyclerView recyclerViewImages;
    ArrayList<CenterPostsResponse.DataBean.PostrepliesBean> replies;
    Integer post_id;
    private PostsViewModel mViewModel;
    boolean isUserLiked;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        mViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        comments_recycle = view.findViewById(R.id.comment_recycle);
        tvAddFirst = view.findViewById(R.id.tvAddFirst);
        recyclerViewImages = view.findViewById(R.id.post_images);
        post = view.findViewById(R.id.post);
        name = view.findViewById(R.id.name);
        time = view.findViewById(R.id.time);
        img = view.findViewById(R.id.item_img);
        like = view.findViewById(R.id.like);
        comment = view.findViewById(R.id.comment);
        tvAddComment = view.findViewById(R.id.addCount);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        addReply = view.findViewById(R.id.add);
        addReply.requestFocus();
        if (getArguments() != null) {
            if (getArguments().containsKey("images")) {
                // if (postList.get(i).getImages()!= null &&postList.get(i).getImages().size()>0){
                post.setVisibility(View.GONE);
                recyclerViewImages.setVisibility(View.VISIBLE);
                ArrayList<String> images = getArguments().getStringArrayList("images");
                ViewsUtils.setupDiffRecycle(recyclerViewImages, images, getContext());
                // }
            } else {
                if (getArguments().containsKey("post")) {
                    post.setText(getArguments().getString("post"));
                }
            }
            name.setText(getArguments().getString("name"));
            time.setText(getArguments().getString("date"));
            like.setText(getArguments().getString("like"));
            isUserLiked = getArguments().getBoolean("islike");
            comment.setText(getArguments().getString("comment"));
            if (isUserLiked) {
                like.setTextColor(getActivity().getResources().getColor(R.color.blue));
                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.liked, 0);
            }
            post_id = getArguments().getInt("id");
            Glide.with(getContext())
                    .load(getArguments().getString("img"))
                    .into(img);
            mViewModel.getPostReplies(post_id);
        }

        mViewModel.getReplies().observe(this, postrepliesBeans -> {
            if (postrepliesBeans.size() > 0) {
                replyList = new ArrayList<>(postrepliesBeans);
                adapter = new CommentsAdapter(getContext(), replyList);
                comments_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                comments_recycle.setAdapter(adapter);
                ViewCompat.setNestedScrollingEnabled(comments_recycle, true);
            } else {
                tvAddFirst.setVisibility(View.VISIBLE);
            }
        });

        tvAddComment.setOnClickListener(v -> {
            if (PreferenceHelper.getUserId() > 0) {
                if (addReply.getText().toString().isEmpty()) {
                    return;
                } else {
                    mViewModel.addReply(PreferenceHelper.getUserId(), addReply.getText().toString(), post_id);
                }
            } else
                Toast.makeText(getContext(), "يجب تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
        });

        mViewModel.getAddedReply().observe(this, reply -> {
            if (replyList == null) {
                addReply.setText("");
                tvAddFirst.setVisibility(View.GONE);
                CenterPostsResponse.DataBean.PostrepliesBean newRate = new CenterPostsResponse.DataBean.PostrepliesBean();
                newRate.setReply(reply.getReply());
                newRate.setCreated(reply.getCreated());
                replyList = new ArrayList<>();
                replyList.add(newRate);
                adapter = new CommentsAdapter(getContext(), replyList);
                comments_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                comments_recycle.setAdapter(adapter);
            } else {
                addReply.setText("");
                /// nestedScrollView.fullScroll(View.FOCUS_DOWN);
                CenterPostsResponse.DataBean.PostrepliesBean newRate = new CenterPostsResponse.DataBean.PostrepliesBean();
                newRate.setReply(reply.getReply());
                newRate.setCreated(reply.getCreated());
                newRate.setUser(new PostRepliesResponse.ReplyBean.UserBean(PreferenceHelper.getUserId(), PreferenceHelper.getUserName(), PreferenceHelper.getCurrentPhoto()));
                replyList.add(adapter.getItemCount(), newRate);
                comments_recycle.scrollToPosition(adapter.getItemCount());
                adapter.notifyDataSetChanged();
            }
        });

        like.setOnClickListener(v ->
        {
            if (PreferenceHelper.getUserId() > 0) {
                if (!isUserLiked) {
                    mViewModel.addLike(PreferenceHelper.getUserId(), post_id);
                } else {
                    mViewModel.removeLike(PreferenceHelper.getUserId(), post_id);
                }
            } else
                Snackbar.make(view, "يجب تسجيل الدخول اولا ", Snackbar.LENGTH_SHORT).show();
        });

        mViewModel.getAddedLike().observe(this, aBoolean -> {
            if (aBoolean) {
                like.setText(String.valueOf(Integer.valueOf(like.getText().toString()) + 1));
                like.setTextColor(getActivity().getResources().getColor(R.color.blue));
                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.liked, 0);
                isUserLiked = true;
            }
        });

        mViewModel.getRemovedLike().observe(this, aBoolean -> {
            if (aBoolean) {
                like.setText(String.valueOf(Integer.valueOf(like.getText().toString()) - 1));
                like.setTextColor(getActivity().getResources().getColor(R.color.darkgrey));
                like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.like, 0);
                isUserLiked = false;
            }
        });

//        getDialog().setOnShowListener(dialog -> {
//            BottomSheetDialog d = (BottomSheetDialog) dialog;
//            FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
//            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bottomSheet.getParent();
//            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//            bottomSheetBehavior.setPeekHeight(bottomSheet.getHeight());
//            coordinatorLayout.getParent().requestLayout();
//        });
        return view;
    }
}