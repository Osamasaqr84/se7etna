package com.codesroots.osamaomar.sehetna.presentation.mainfragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter.AllResultAdapter;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter.AllTypesAdapter;

import com.codesroots.osamaomar.sehetna.presentation.details.CenterFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment /*implements OnMapReadyCallback*/ {

    private AllResultAdapter adapter;
    public MainViewModel getmViewModel() {
        return mViewModel;
    }

    MainViewModel mViewModel;
    private BottomSheetBehavior bottomSheetBehaviorCat;
    RecyclerView types, searchRec;
    ShimmerRecyclerView recyclerView;
    ProgressBar progressBar;
    View search_view;
    LinearLayout llBottomSheet, showLists;
    Integer page = 1;
    List<Post> postsList;
    protected View mView;
    private TextView noposts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        llBottomSheet = view.findViewById(R.id.bottom_sheet);
        showLists = view.findViewById(R.id.showMore);
        search_view = view.findViewById(R.id.search_view);
        progressBar = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.posts_recycle);
        noposts = view.findViewById(R.id.noposts);
        recyclerView.showShimmerAdapter();
        types = view.findViewById(R.id.types);
        ((MainActivity) getActivity()).changeBNVisibility(1);
        showLists.setOnClickListener(v -> bottomSheetBehaviorCat.setState(BottomSheetBehavior.STATE_COLLAPSED));
        bottomSheetBehaviorCat = BottomSheetBehavior.from(llBottomSheet);
        ((MainActivity) getActivity()).gotoCurrentLocation();
        // change the state of the bottom sheet
        bottomSheetBehaviorCat.setHideable(true);
        bottomSheetBehaviorCat.setState(BottomSheetBehavior.STATE_HIDDEN);
        new Handler().postDelayed(() -> bottomSheetBehaviorCat.setState(BottomSheetBehavior.STATE_COLLAPSED), 2000);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                Log.d("lastVisible", String.valueOf(lastVisibleItem));
                if (lastVisibleItem == recyclerView.getAdapter().getItemCount() - 1) {
                    page++;
                    mViewModel.getPosts(page, PreferenceHelper.getUserId()); // Todo Dynamic
                }
            }
        });

        mViewModel.getCategories();
        mViewModel.getCategoriesLD().observe(this, categories -> {
            types.setAdapter(new AllTypesAdapter(getActivity(),categories, mViewModel));
            ViewCompat.setNestedScrollingEnabled(types, false);
        });

        mViewModel.getPosts(page,PreferenceHelper.getUserId()); // Todo Dynamic

        mViewModel.error.observe(this,throwable ->
                        Toast.makeText(getActivity(),throwable.toString(),Toast.LENGTH_SHORT).show()
                );
        mViewModel.getPostsLD().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {

                if (posts.size()==0 && page == 1)
                    noposts.setVisibility(View.VISIBLE);
                if (page == 1) {
                    postsList = new ArrayList<>(posts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new AllResultAdapter(postsList, getActivity(), mViewModel);
                    recyclerView.setAdapter(adapter);
                } else {
                    postsList.addAll(adapter.getItemCount(), posts);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(adapter.getItemCount() - 19);
                }
            }
        });

        mViewModel.getGotoDetails().observe(this, new Observer<SearchEntity>() {
            @Override
            public void onChanged(@Nullable SearchEntity searchEntity) {
                bottomSheetBehaviorCat.setHideable(true);
                bottomSheetBehaviorCat.setState(BottomSheetBehavior.STATE_HIDDEN);
                ((MainActivity) getActivity()).changeBNVisibility(1);
                ((MainActivity) getActivity()).changeSBVisibility(1);
                ((MainActivity) getActivity()).clearMap();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = new CenterFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("healthcare_id",searchEntity.getId());
                        bundle.putParcelable("currItem", searchEntity);
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.main_view, fragment).commit();
                    }
                }, 1000);
            }
        });

        mViewModel.getAddedLike().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                   // postsList.get(mViewModel.getCurrentItem() ).setAddedLike(true);
                    postsList.get(mViewModel.getCurrentItem() ).setLiked(true);
                    postsList.get(mViewModel.getCurrentItem()).setLikeCount(postsList.get(mViewModel.getCurrentItem()).getLikeCount()+1);
                    adapter.notifyItemChanged(mViewModel.getCurrentItem() );
                }
            }
        });

        mViewModel.getRemovedLike().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    postsList.get(mViewModel.getCurrentItem()).setLiked(false);
                   // postsList.get(mViewModel.getCurrentDeleteItem()).setAddedLike(false);
                    postsList.get(mViewModel.getCurrentItem()).setLikeCount(postsList.get(mViewModel.getCurrentItem()).getLikeCount() - 1);
                    adapter.notifyItemChanged(mViewModel.getCurrentItem());
                }
            }
        });
        this.mView = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle backbutton's click listener
                    if (bottomSheetBehaviorCat.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehaviorCat.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        return true;
                    }
                    else
                        return true;
                } else {
                    getActivity().onBackPressed();
                    return true;
                }
            }
        });
    }
}
