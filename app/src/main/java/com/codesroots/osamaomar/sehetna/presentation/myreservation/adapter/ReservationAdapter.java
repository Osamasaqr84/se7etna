package com.codesroots.osamaomar.sehetna.presentation.myreservation.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.MyReservation;
import com.codesroots.osamaomar.sehetna.R;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private Context mContext;
    List<MyReservation.DataBean> myReservations;
    public ReservationAdapter(FragmentActivity activity, List<MyReservation.DataBean> myReservationData) {
        mContext = activity;
        myReservations = myReservationData;
    }

    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_reservation, parent, false);
        return new ReservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(myReservations.get(position).getHealthcare().getName());
        viewHolder.resdate.setText(myReservations.get(position).getReservation_date());
        Glide.with(mContext)
                .load(myReservations.get(position).getHealthcare().getMainphoto())
                .into(viewHolder.img);
        viewHolder.ivPhone.setOnClickListener(v -> makePhonecall(myReservations.get(position).getHealthcare().getPhone1()));
   }

    @Override
    public int getItemCount() {
        return myReservations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name,resdate;
        ImageView img ,ivPhone;
        public ViewHolder(View v) {
            super(v);
            mView=v;
            name =mView.findViewById(R.id.name);
            resdate =mView.findViewById(R.id.rese_date);
            img =mView.findViewById(R.id.img);
            ivPhone =mView.findViewById(R.id.ivPhone);
        }
    }

    private void makePhonecall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.CALL_PHONE}, 15);
            return;
        }
        mContext.startActivity(intent);
    }
}
