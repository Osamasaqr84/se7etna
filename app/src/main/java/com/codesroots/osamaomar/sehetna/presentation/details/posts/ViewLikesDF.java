package com.codesroots.osamaomar.sehetna.presentation.details.posts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.viewmodel.PostsViewModel;

import java.util.ArrayList;

public class ViewLikesDF extends BottomSheetDialogFragment {
    RecyclerView like_recycle;
    EditText addReply;
    TextView post, tvAddFirst, name, time, like, comment, tvAddComment;

    ArrayList<CenterPostsResponse.DataBean.PostlikesBean> likeList;
    private LikesAdapter adapter;

    Integer post_id;
    private PostsViewModel mViewModel;

    public static ViewLikesDF newInstance() {
        return new ViewLikesDF();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_likes, container,
                false);

        mViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);

        like_recycle = view.findViewById(R.id.likes_recycle);
        tvAddFirst = view.findViewById(R.id.tvAddFirst);
       
        if (getArguments() != null) {

            if (getArguments().containsKey("likes")) {
                ArrayList<CenterPostsResponse.DataBean.PostlikesBean> postLikesBeans = getArguments().getParcelableArrayList("likes");
                if (postLikesBeans.size() > 0) {

                    adapter = new LikesAdapter(getContext(), postLikesBeans);
                    like_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                    like_recycle.setAdapter(adapter);
                    ViewCompat.setNestedScrollingEnabled(like_recycle, true);

                } else {

                    tvAddFirst.setVisibility(View.VISIBLE);

                }



            } else {
                tvAddFirst.setVisibility(View.VISIBLE);
            }


        }








        return view;

    }


}