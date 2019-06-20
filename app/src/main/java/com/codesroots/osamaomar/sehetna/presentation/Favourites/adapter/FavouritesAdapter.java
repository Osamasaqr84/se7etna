package com.codesroots.osamaomar.sehetna.presentation.Favourites.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.presentation.Favourites.FavouritesViewModel;


import java.util.ArrayList;
import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {


    private Context mContext;
    public List<SearchEntity> getSearchEntityList() {
        return searchEntityList;
    }
    ArrayList<SearchEntity> searchEntityList;
    public List<SearchEntity> getBaseList() {
        return baseList;
    }
    ArrayList<SearchEntity> baseList;
    FavouritesViewModel favouritesViewModel;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    String state = "fullList";

 public FavouritesAdapter(List<SearchEntity> list , Context context  , FavouritesViewModel favouritesViewModelC )
    {
        mContext  = context;
        searchEntityList = new ArrayList<>(list);
        baseList = new ArrayList<>(list);
        favouritesViewModel  = favouritesViewModelC;
    }

    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_favourites, parent, false);
        return new FavouritesAdapter.ViewHolder(view);
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
        viewHolder.hosType.setText(searchEntityList.get(position).getHosType());
        viewHolder.time.setText(searchEntityList.get(position).getTime());
        viewHolder.distance.setText(searchEntityList.get(position).getDistance()+"km") ;
        viewHolder.adress.setText(searchEntityList.get(position).getAdress());


        if( searchEntityList.get(position).getState()){
            viewHolder.state.setText( "مفتوح");
            viewHolder.state.setTextColor(mContext.getResources().getColor(R.color.lightgreen));
        }else{
            viewHolder.state.setText( "مغلق");
            viewHolder.state.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        if( searchEntityList.get(position).getVerbalState()!=null  && searchEntityList.get(position).getVerbalState().equals("") ){
            viewHolder.verbalState .setVisibility(View.GONE);
            viewHolder.time .setVisibility(View.GONE);
            viewHolder.dash.setVisibility(View.GONE);
        }
        else{

            viewHolder.verbalState .setVisibility(View.VISIBLE);
            viewHolder.time .setVisibility(View.VISIBLE);
            viewHolder.dash.setVisibility(View.VISIBLE);

            viewHolder.verbalState.setText(searchEntityList.get(position).getVerbalState());}



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

        viewHolder.ivroute.setOnClickListener(v->{
            favouritesViewModel.removeFromFavourites(searchEntityList.get(position).getId());
            favouritesViewModel.setCurrentItem(position);
        });

        viewHolder.mView.setOnClickListener(v->{
        favouritesViewModel.getGotoDetails().postValue(searchEntityList.get(position));});
    }


    @Override
    public int getItemCount() {
        return searchEntityList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name,hosType,time ,distance ,adress , state , verbalState ,rateCount ,dash;
        RatingBar ratingBar;
        ImageView img , ivroute;
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
        }


    }

    public void filter(String text) {
        searchEntityList.clear();
        if(text.isEmpty()){
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
            String searchText = favouritesViewModel.getSearchText().getValue() ;
            if (searchText.isEmpty() ){}else {
                this.filter(searchText);
            }


        } else{

            for(SearchEntity item: baseList){
                if(item.getRating()!=null &&item.getRating().equals(rating) ){
                    String searchText = favouritesViewModel.getSearchText().getValue() ;

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

    public void remove(int position , String state) {

     if( state.equals("notfiltered")){
         Log.d("beforebaseListCount", String.valueOf(baseList.size()));
         baseList.remove(position );
         searchEntityList.remove(position );
         Log.d("afterbaseListCount", String.valueOf(baseList.size()));

         //  searchEntityList.remove(position );
     /* notifyDataSetChanged();
    notifyItemRangeChanged(position,baseList.size());*/
         notifyItemRemoved(position);
         notifyItemRangeChanged(position,baseList.size());
     }else if (state.equals("filtered")){

         baseList.remove(searchEntityList.get(position ) );
         searchEntityList.remove(position );
         notifyItemRemoved(position);
         notifyItemRangeChanged(position,baseList.size());

     }


    }

}
