package com.codesroots.osamaomar.sehetna.presentation.details.posts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.viewmodel.PostsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostsFragment extends Fragment {

    private PostsViewModel mViewModel;
    private PostsAdapter adapter;
    RecyclerView posts_recycle;
    private int helthcare_id;
    List<Post> postsList ;
    Integer page = 1;
    private TextView notfound;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.posts_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        posts_recycle = view.findViewById(R.id.posts_recycle);
        notfound = view.findViewById(R.id.notfound);
        helthcare_id = getArguments().getInt("helthcare_id",0);
        mViewModel.getCenterPosts(page,helthcare_id , PreferenceHelper.getUserId()); // Todo Dynamic

        mViewModel.getPostsLD().observe(this, posts -> {
            if (posts.size()>0)
            { if (page==1){
                postsList=new ArrayList<>(posts);
                posts_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter =  new PostsAdapter(postsList , getActivity() ,mViewModel );
                posts_recycle.setAdapter(adapter);
                ViewCompat.setNestedScrollingEnabled(posts_recycle, true);
            }else{
                postsList.addAll(adapter.getItemCount() ,posts);
                adapter.notifyDataSetChanged();
                posts_recycle.scrollToPosition(adapter.getItemCount()-19);
            }}
            else
                notfound.setVisibility(View.VISIBLE);
        });

        posts_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = ((LinearLayoutManager)Objects.requireNonNull(recyclerView.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                Log.d("lastVisible" , String.valueOf(lastVisibleItem) );
                if(lastVisibleItem == recyclerView.getAdapter().getItemCount()-1){
                    page++;
                    mViewModel.getCenterPosts(page,1 ,1);  // Todo Dynamic
                }
            }
        });

        mViewModel.getAddedLike().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                 //   postsList.get(mViewModel.getCurrentItem() ).setAddedLike(true);
                    postsList.get(mViewModel.getCurrentItem() ).setLiked(true);
                    postsList.get(mViewModel.getCurrentItem()).setLikeCount(postsList.get(mViewModel.getCurrentItem()).getLikeCount()+1);
                    adapter.notifyItemChanged(mViewModel.getCurrentItem() );
                }
            }
        });
        mViewModel.getRemovedLike().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if (aBoolean){
                    postsList.get(mViewModel.getCurrentItem()).setLiked(false);
                   // postsList.get(mViewModel.getCurrentDeleteItem() ).setAddedLike(false);
                    postsList.get(mViewModel.getCurrentItem() ).setLikeCount(postsList.get(mViewModel.getCurrentItem() ).getLikeCount()-1);
                    adapter.notifyItemChanged(mViewModel.getCurrentItem() );
                }
            }
        });
        return  view;
    }


}
