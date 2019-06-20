package com.codesroots.osamaomar.sehetna.presentation.details.ratesFragmenr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.RatingResponse;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateDiff;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getDateObject;
import static com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils.getdate;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.CustomView> {


    private final Context mContext;
    private MainActivity mainActivity;
    List<RatingResponse.DataBean> rateList;

    public RatesAdapter(List<RatingResponse.DataBean> list, Context context) {
        mContext = context;
        rateList = list;
        mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public RatesAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_item_ratings, viewGroup, false);

        return new RatesAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatesAdapter.CustomView customView, int i) {

        if (rateList.get(i).getUser() != null) {

            customView.name.setText(rateList.get(i).getUser().getUsername());
            Glide.with(mContext)
                    .load(rateList.get(i).getUser().getPhoto())
                    .fitCenter().placeholder(R.drawable.nurse)
                    .into(customView.imageView);
        }
        customView.ratingBar.setRating(rateList.get(i).getRate());

        customView.post.setText(rateList.get(i).getComment());

        if (rateList.get(i).getCreated() != null && rateList.get(i).getCreated() != "") {

            if (getDateDiff(getDateObject(rateList.get(i).getCreated()),
                    Calendar.getInstance().getTime(), TimeUnit.HOURS) > 24) {

                customView.time.setText(
                        getdate(rateList.get(i).getCreated()));
            } else {
                Long hours = getDateDiff(getDateObject(rateList.get(i).getCreated()),
                        Calendar.getInstance().getTime(), TimeUnit.HOURS);
                if (hours == 0) hours = Long.valueOf(1);

                customView.time.setText(Long.toString(hours) + "h");
            }
        }
    }

    @Override
    public int getItemCount() {
        return rateList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView imageView;
        private TextView time, post, name;
        RatingBar ratingBar;

        private CustomView(View view) {
            super(view);
            mView = view;

            name = mView.findViewById(R.id.name);
            time = mView.findViewById(R.id.time);
            post = mView.findViewById(R.id.post);
            imageView = mView.findViewById(R.id.img);
            ratingBar = mView.findViewById(R.id.rating);

        }
    }

}
