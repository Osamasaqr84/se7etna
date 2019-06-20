package com.codesroots.osamaomar.sehetna.presentation.Favourites;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.Favourites.adapter.FavouritesAdapter;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.details.CenterFragment;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private FavouritesViewModel mViewModel;

    FavouritesAdapter listAdapter;
    List<SearchEntity> recDataList;
    EditText etSearch;
    TextView tvNameB, filter, starCount;
    RecyclerView searchRec;
    ImageView removeList, removeListB;
    private static BottomSheetBehavior bottomSheetBehaviorRes;
    ConstraintLayout mainLayout;

    Integer page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        mViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);
        etSearch = view.findViewById(R.id.etnameB);
        filter = view.findViewById(R.id.filter);
        starCount = view.findViewById(R.id.filterCount);
        // removeList =view.findViewById(R.id.removeList);
        removeListB = view.findViewById(R.id.removeListR);
        searchRec = view.findViewById(R.id.recycler);
        tvNameB = view.findViewById(R.id.tvNameB);
        mainLayout = view.findViewById(R.id.mainLayout);
        bottomSheetBehaviorRes = BottomSheetBehavior.from(mainLayout);
        bottomSheetBehaviorRes.setHideable(false);

        // change the state of the bottom sheet
        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_EXPANDED);

        ((MainActivity) getActivity()).changeBNVisibility(0);
        ((MainActivity) getActivity()).changeSBVisibility(0);

        mViewModel.getSearchText().setValue("");

        //29.966230,31.245670

        mViewModel.getData(page, PreferenceHelper.getUserId(), PreferenceHelper.geCurrentlat(), PreferenceHelper.getCurrentlang());
        tvNameB.setOnClickListener(v -> {
            tvNameB.setVisibility(View.GONE);
            etSearch.setVisibility(View.VISIBLE);
            etSearch.requestFocus();
        });

        removeListB.setOnClickListener(v -> {
            bottomSheetBehaviorRes.setHideable(true);
            bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);
            ((MainActivity) getActivity()).changeBNVisibility(1);
            ((MainActivity) getActivity()).changeSBVisibility(1);
            ((MainActivity) getActivity()).clearMap();
            new Handler().postDelayed(() -> getFragmentManager().beginTransaction()
                    /* .setCustomAnimations(R.anim.slide_out_up ,R.anim.slide_in_up )*/
                    .replace(R.id.main_view, new MainFragment())
                    .addToBackStack(null).commit(), 1000);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") && mViewModel.getSearchText().getValue().length() == 1
                        && !starCount.getText().toString().trim().contains("نج")) {
                    mViewModel.setState("notfiltered");
                } else {
                    mViewModel.setState("filtered");
                }
                mViewModel.getSearchText().setValue(s.toString());
                listAdapter.filter(s.toString());
                Log.d("", "afterTextChanged: s" + s.toString());
            }
        });


        mViewModel.getNewData().observe(this, searchEntities -> {

            recDataList = new ArrayList<>(searchEntities);
            recDataList = searchEntities;
            listAdapter = new FavouritesAdapter(recDataList, getContext(), mViewModel);
            searchRec.setLayoutManager(new LinearLayoutManager(getActivity()));
            searchRec.setAdapter(listAdapter);
            ViewCompat.setNestedScrollingEnabled(searchRec, true);
            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater1 = getLayoutInflater();
                    builder.setTitle("بحث حسب التقييم");
                    View dialogLayout = inflater1.inflate(R.layout.dialog_rating, null);
                    // final RatingBar ratingBar = dialogLayout.findViewById(R.id.ratingBar);
                    builder.setView(dialogLayout);
                      /*      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getContext(), "Rating is " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                                    listAdapter.filterStar(ratingBar.getRating());
                                }
                            });*/
                    final AlertDialog ad = builder.show();
                    ((Button) dialogLayout.findViewById(R.id.star)).setOnClickListener((View v2) -> {
                        starCount.setText("نجمة");
                        starCount.setTextColor(getResources().getColor(R.color.etblue));
                        ad.dismiss();
                        listAdapter.filterStar(1f);
                        mViewModel.setState("filtered");
                    });
                    ((Button) dialogLayout.findViewById(R.id.star2)).setOnClickListener((View v2) -> {
                        starCount.setText("نجمتان");
                        starCount.setTextColor(getResources().getColor(R.color.etblue));
                        ad.dismiss();
                        listAdapter.filterStar(2f);
                        mViewModel.setState("filtered");
                    });
                    ((Button) dialogLayout.findViewById(R.id.star3)).setOnClickListener((View v2) -> {
                        starCount.setText("3 نجوم");
                        starCount.setTextColor(getResources().getColor(R.color.etblue));
                        ad.dismiss();
                        listAdapter.filterStar(3f);
                        mViewModel.setState("filtered");
                    });
                    ((Button) dialogLayout.findViewById(R.id.star4)).setOnClickListener((View v2) -> {
                        starCount.setText("4 نجوم");
                        starCount.setTextColor(getResources().getColor(R.color.etblue));
                        ad.dismiss();
                        listAdapter.filterStar(4f);
                        mViewModel.setState("filtered");
                    });
                    ((Button) dialogLayout.findViewById(R.id.star5)).setOnClickListener((View v2) -> {
                        starCount.setText("5 نجوم");
                        starCount.setTextColor(getResources().getColor(R.color.etblue));
                        ad.dismiss();
                        listAdapter.filterStar(5f);
                        mViewModel.setState("filtered");
                    });
                    ((Button) dialogLayout.findViewById(R.id.star6)).setOnClickListener((View v2) -> {
                        starCount.setText("كل التقييم");
                        starCount.setTextColor(getResources().getColor(R.color.starcount));
                        ad.dismiss();
                        listAdapter.filterStar(0.0f);
                        mViewModel.setState("notfiltered");
                    });
                }
            });
        });


        mViewModel.getGotoDetails().observe(this, new Observer<SearchEntity>() {
            @Override
            public void onChanged(@Nullable SearchEntity searchEntity) {
                bottomSheetBehaviorRes.setHideable(true);
                bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);
                ((MainActivity) getActivity()).changeBNVisibility(1);
                ((MainActivity) getActivity()).changeSBVisibility(1);
                ((MainActivity) getActivity()).clearMap();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = new CenterFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("currItem", searchEntity);
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.main_view, fragment).commit();
                    }
                }, 1000);
            }
        });

        mViewModel.getremovedFromFavourites().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {

                    listAdapter.remove(mViewModel.getCurrentItem(), mViewModel.getState());
                }

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    if (bottomSheetBehaviorRes.getState() == BottomSheetBehavior.STATE_EXPANDED)
                        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    if (bottomSheetBehaviorRes.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                        bottomSheetBehaviorRes.setHideable(true);
                        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);

                        ((MainActivity) getActivity()).changeBNVisibility(1);
                        ((MainActivity) getActivity()).changeSBVisibility(1);
                        ((MainActivity) getActivity()).clearMap();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                getFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).commit();
                            }
                        }, 1000);

                    }
                    return true;
                } else {
                    return true;
                }

            }
        });
    }

    public void collapseResult() {

        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
