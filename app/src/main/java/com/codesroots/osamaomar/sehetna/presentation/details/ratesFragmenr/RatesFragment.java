package com.codesroots.osamaomar.sehetna.presentation.details.ratesFragmenr;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.AddRateResponse;
import com.codesroots.osamaomar.sehetna.Entities.RatingResponse;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.Utils.ViewsUtils;

import java.util.ArrayList;
import java.util.List;

public class RatesFragment extends Fragment {

    private RatesViewModel mViewModel;
    RecyclerView rates_recycle;
    List<RatingResponse.DataBean> ratesList;
    private RatesAdapter adapter;
    EditText comment;
    RatingBar ratingBar;
    TextView publish;
    private int helthcare_id;
    private NestedScrollView nestedScrollView;
    private ImageView item_img;
    private Group group;

    public static RatesFragment newInstance() {
        return new RatesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rates_fragment, container, false);

        helthcare_id = getArguments().getInt("helthcare_id");
        mViewModel = ViewModelProviders.of(this).get(RatesViewModel.class);
        rates_recycle = view.findViewById(R.id.comment_recycle);
        group = view.findViewById(R.id.group);
        comment = view.findViewById(R.id.comment);
        ratingBar = view.findViewById(R.id.rate);
        publish = view.findViewById(R.id.publishComment);
        item_img = view.findViewById(R.id.item_img);
        if (PreferenceHelper.getUserId() > 0)
            Glide.with(getContext()).load(PreferenceHelper.getCurrentPhoto()).into(item_img);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        rates_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (helthcare_id != 0) {
            mViewModel.getCenterEvaluation(helthcare_id);
            group.setVisibility(View.VISIBLE);
        } else {
            group.setVisibility(View.GONE);
            ratesList = getArguments().getParcelableArrayList("rates");
            adapter = new RatesAdapter(ratesList, getActivity());
            rates_recycle.setAdapter(adapter);
        }

        mViewModel.getPostsLD().observe(this, new Observer<List<RatingResponse.DataBean>>() {
            @Override
            public void onChanged(@Nullable List<RatingResponse.DataBean> rates) {

                //  if (page==1){
                ratesList = new ArrayList<>(rates);

                adapter = new RatesAdapter(ratesList, getActivity());
                rates_recycle.setAdapter(adapter);
                ViewCompat.setNestedScrollingEnabled(rates_recycle, true);
       /*         }else{

                    postsList.addAll(adapter.getItemCount() ,posts);
                    adapter.notifyDataSetChanged();
                    rates_recycle.scrollToPosition(adapter.getItemCount()-19);
                }*/

            }
        });

        publish.setOnClickListener(v -> {
            if (comment.getText().equals("")) {
                Toast.makeText(getContext(), "رجاء أضف تقييمك", Toast.LENGTH_LONG).show();
                return;
            } else if (PreferenceHelper.getUserId()==0) {
                Toast.makeText(getContext(), "  يجب تسجيل الدخول ", Toast.LENGTH_LONG).show();
            }
            else {
                mViewModel.addCenterEvaluation(PreferenceHelper.getUserId(), (int) ratingBar.getRating(), comment.getText().toString(), helthcare_id);
                ViewsUtils.hideKeyboard(getActivity());
            }
        });

        mViewModel.getAddRateLD().observe(this, new Observer<AddRateResponse>() {
            @Override
            public void onChanged(@Nullable AddRateResponse addRateResponse) {

                comment.setText("");
                nestedScrollView.fullScroll(View.FOCUS_DOWN);
                RatingResponse.DataBean newRate = new RatingResponse.DataBean();
                newRate.setComment(addRateResponse.getHccareEvaluation().getComment());
                newRate.setRate(addRateResponse.getHccareEvaluation().getRate());
                newRate.setCreated(addRateResponse.getHccareEvaluation().getCreated());
                //
                ratesList.add(adapter.getItemCount(), newRate);
                adapter.notifyDataSetChanged();
                rates_recycle.scrollToPosition(adapter.getItemCount());
            }
        });

        return view;
    }

}
