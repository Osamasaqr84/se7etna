package com.codesroots.osamaomar.sehetna.presentation.details.photos;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.osamaomar.sehetna.Entities.PhotosResponse;
import com.codesroots.osamaomar.sehetna.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotosFragment extends Fragment {

    private PhotosViewModel mViewModel;
    RecyclerView photos_recycle;
    Integer page = 1;
    private List<PhotosResponse.DataBean.HcphotosBean> postsList;
    private PhotosAdapter adapter;
    private  int helthcare_id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.photos_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(PhotosViewModel.class);
        photos_recycle = view.findViewById(R.id.photos_recycle);
        helthcare_id = getArguments().getInt("helthcare_id",0);
        photos_recycle.setLayoutManager(new GridLayoutManager(getActivity(),2));

        if (helthcare_id!=0)
        mViewModel.getPhotos(page , helthcare_id);

        else
        {
            postsList=getArguments().getParcelableArrayList("photos");
            adapter =  new PhotosAdapter(postsList , getActivity() );
            photos_recycle.setAdapter(adapter);
        }

        mViewModel.getPostsLD().observe(this, new Observer<List<PhotosResponse.DataBean.HcphotosBean>>() {
            @Override
            public void onChanged(@Nullable List<PhotosResponse.DataBean.HcphotosBean> photos) {
                if (page==1){
                    postsList=new ArrayList<>(photos);
                    adapter =  new PhotosAdapter(postsList , getActivity() );
                    photos_recycle.setAdapter(adapter);
                    ViewCompat.setNestedScrollingEnabled(photos_recycle, true);
                }else{
                    postsList.addAll(adapter.getItemCount() ,photos);
                    adapter.notifyDataSetChanged();
                    photos_recycle.scrollToPosition(adapter.getItemCount()-19);
                }
            }
        });

        photos_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager)Objects.requireNonNull(recyclerView.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                Log.d("lastVisible" , String.valueOf(lastVisibleItem) );
                if(lastVisibleItem == recyclerView.getAdapter().getItemCount()-1){
                    page++;
                    mViewModel.getPhotos(page,helthcare_id);  // Todo Dynamic
                }
            }
        });
        

        return  view;
    }


}
