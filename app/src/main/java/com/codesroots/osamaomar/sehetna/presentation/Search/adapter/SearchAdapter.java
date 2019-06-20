package com.codesroots.osamaomar.sehetna.presentation.Search.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.Search.viewmodel.ResultViewModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<SearchEntity> searchEntityList=new ArrayList<>(),baseList=new ArrayList<>();
    private Context mContext;
    private ResultViewModel resultViewmodel;
    private String state = "fullList";
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public SearchAdapter(List<SearchEntity> list ,Context context ,ResultViewModel resultViewmodelC )
    {
        mContext  = context;
        baseList.addAll(list);
        searchEntityList.addAll(list);
        resultViewmodel  = resultViewmodelC;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_results_search, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (searchEntityList.get(position).getRating()!=null && searchEntityList.get(position).getRating()>0) {
          //  ViewHolder.ratecount.setText("(" + searchEntityList.get(position).getCount() + ")");
            viewHolder.ratingBar.setRating(searchEntityList.get(position).getRating());
            viewHolder.rateCount.setText("("+searchEntityList.get(position).getRate_count().toString()+")");
        }

        else
        {// ViewHolder.ratecount.setText("(0)");
            viewHolder.ratingBar.setRating(0);
        }

        viewHolder.name.setText(searchEntityList.get(position).getName());
        viewHolder.hosType.setText(searchEntityList.get(position).getSpecialist());
      //  viewHolder.hosType.setText(searchEntityList.get(position).getHosType());
      //  viewHolder.time.setText(searchEntityList.get(position).getTime());
        viewHolder.distance.setText(searchEntityList.get(position).getDistance()+"km") ;
        viewHolder.adress.setText(searchEntityList.get(position).getAdress());

    if( searchEntityList.get(position).getState()){
        viewHolder.state.setText( "مفتوح");
        viewHolder.state.setTextColor(mContext.getResources().getColor(R.color.lightgreen));
    }else{
        viewHolder.state.setText( "مغلق");
        viewHolder.state.setTextColor(mContext.getResources().getColor(R.color.red));
    }
    if( searchEntityList.get(position).getVerbalState()==null  || searchEntityList.get(position).getVerbalState().equals("") ){
        viewHolder.verbalState .setVisibility(View.GONE);
       viewHolder.time .setVisibility(View.GONE);
        viewHolder.dash.setVisibility(View.GONE);
    }
    else{
        viewHolder.verbalState .setVisibility(View.VISIBLE);
        viewHolder.time .setVisibility(View.VISIBLE);
       if(searchEntityList.get(position).getOpeningHour()==null && searchEntityList.get(position).getClosingHour()!=null ){
           viewHolder.time .setText(searchEntityList.get(position).getClosingHour());
       }else if(searchEntityList.get(position).getOpeningHour()!=null && searchEntityList.get(position).getClosingHour()==null ){
           viewHolder.time .setText(searchEntityList.get(position).getOpeningHour());
       }
    viewHolder.dash.setVisibility(View.VISIBLE);
    viewHolder.verbalState.setText(searchEntityList.get(position).getVerbalState());
    }

        String imgUrl ="";
        if (searchEntityList.get(position).getGoogleItem()) {
            imgUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=80&MAX_HEIGHT=70"
                    +"&photoreference=" + searchEntityList.get(position).getImgUrl() +
                    "&key=AIzaSyDPa2J9AKnxyYTcKTUzH3rmFqiXTIezXuM";
        } else{
            imgUrl = searchEntityList.get(position).getIconurl();
        }

        Glide.with(mContext)
                .load(imgUrl)
                .into(viewHolder.img);

        if (baseList.get(position).getGoogleItem()){
            viewHolder.ivroute.setVisibility(View.VISIBLE);
            viewHolder.ivPhone.setVisibility(View.GONE);
        }else{
            viewHolder.ivPhone.setVisibility(View.VISIBLE);
            viewHolder.ivroute.setVisibility(View.GONE);
        }
        viewHolder.ivroute.setOnClickListener(v->{
            Double destinationLatitude = baseList.get(position).getLat();
            Double destinationLongitude = baseList.get(position).getLng();
            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", destinationLatitude,
                    destinationLongitude, baseList.get(position).getName() );
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            mContext.startActivity(intent);
        });


        if (baseList.get(position).getPhoneNumber()!=null && !  baseList.get(position).getPhoneNumber().equals("") ){

            viewHolder.ivPhone.setOnClickListener(v->{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + baseList.get(position).getPhoneNumber()));
                mContext.startActivity(intent);
            });
        }

        viewHolder.mView.setOnClickListener(v->{
        resultViewmodel.getGotoDetails().postValue(searchEntityList.get(position));});
        viewHolder.ivFavN.setOnClickListener(v->{
            resultViewmodel.setCurrentItem(position);
            resultViewmodel.addToFavourites(((MainActivity)mContext).getUserID() , searchEntityList.get(position).getId() );});
    }


    @Override
    public int getItemCount() {
        return searchEntityList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name,hosType,time ,distance ,adress , state , verbalState ,rateCount ,dash;
        RatingBar ratingBar;
        ImageView img , ivroute,ivPhone, ivFavN, ivFav;
        public ViewHolder(View v) {
            super(v);
            mView=v;

            name =mView.findViewById(R.id.name);
            hosType =mView.findViewById(R.id.hosType);
            distance =mView.findViewById(R.id.distance);
            adress =mView.findViewById(R.id.adress);
            state =mView.findViewById(R.id.state);
            verbalState =mView.findViewById(R.id.verbalState);

            time =mView.findViewById(R.id.time5);
            dash =mView.findViewById(R.id.time3);
            ratingBar =mView.findViewById(R.id.rating);
            rateCount =mView.findViewById(R.id.rate_count);
            img =mView.findViewById(R.id.img);
            ivroute =mView.findViewById(R.id.ivRoute);
            ivPhone =mView.findViewById(R.id.ivPhone);
            ivFavN =mView.findViewById(R.id.ivFavN);
            ivFav =mView.findViewById(R.id.ivFav);
        }
    }

    public void filter(String text) {

        searchEntityList.clear();
        if(text.matches("")){
            searchEntityList.addAll(baseList);
        } else{
            text = text.toLowerCase();
            for(SearchEntity item: baseList){
                if(item.getName().toLowerCase().contains(text) ){
                    searchEntityList.add(item);
                }
            }
        }
        if (searchEntityList.size()==0) Toast.makeText(mContext, "لا توجد نتائج لهذا البحث", Toast.LENGTH_LONG).show();
        notifyDataSetChanged();
    }

    public void filterStar(Float rating) {
        searchEntityList.clear();
        if(rating==0.0){
            searchEntityList.addAll(baseList);
            String searchText = resultViewmodel.getSearchText().getValue() ;
            if (searchText.isEmpty() ){}else {
                this.filter(searchText);
            }
        } else{
            for(SearchEntity item: baseList){

                if(  item.getRating()!=null &&   item.getRating().equals(rating) ){
                    String searchText = resultViewmodel.getSearchText().getValue() ;

                    if (searchText.isEmpty() ){searchEntityList.add(item);}else {
                       if (item.getName().toLowerCase().contains(searchText))searchEntityList.add(item);
                    }
                }
            }
            state ="filtered";
        }
        if (searchEntityList.size()==0) Toast.makeText(mContext, "لا توجد نتائج لهذا البحث", Toast.LENGTH_LONG).show();
        notifyDataSetChanged();
    }

}
