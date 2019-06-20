package com.codesroots.osamaomar.sehetna.data.repositories;

import com.codesroots.osamaomar.sehetna.Entities.AddToFavouritesResponse;
import com.codesroots.osamaomar.sehetna.Entities.FavouriteResponse;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;

import com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.Utils.LocationUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class FavouritesRepository {

    private ApiInterface userApi;

    Double currlat , currlng  =  0.0;
    public FavouritesRepository(ApiInterface userApi) {
        this.userApi = userApi;

    }

    public Single<List<SearchEntity>> getFavourites(Integer page ,int userid,  Double lat, Double lng)   {

        currlat = lat;
        currlng = lng;
        return userApi.getfavourites(userid)
                .flatMapIterable(data -> data.getData())
                .map(new Function<FavouriteResponse.DataBean, SearchEntity>() {
                    @Override
                    public SearchEntity apply(FavouriteResponse.DataBean favouriteEntity) throws Exception {

                        SearchEntity favouriteEntity1 = new SearchEntity();
                        double lati =favouriteEntity.getHealthcare().getLat();
                        double longi =favouriteEntity.getHealthcare().getLon();
                        favouriteEntity1.setId(favouriteEntity.getId());
                        favouriteEntity1.setLat(lati);
                        favouriteEntity1.setLng(longi);

                        if (favouriteEntity.getHealthcare().getHccare_evaluations()!=null && favouriteEntity.getHealthcare().getHccare_evaluations().size()>0 ){
                            favouriteEntity1.setRate_count(Math.round(favouriteEntity.getHealthcare().getHccare_evaluations().get(0).getSum()));
                            favouriteEntity1.setRating(Float.valueOf(Math.round(favouriteEntity.getHealthcare().getHccare_evaluations().get(0).getCount()) ) );
                        }


                        if (favouriteEntity.getHealthcare().getHcworkingdays()!=null && favouriteEntity.getHealthcare().getHcworkingdays().size()>0){

                            boolean isBetweenInterval =     DateTimeUtils.isNowBetweenDateTime(
                                    DateTimeUtils.getDateObject(favouriteEntity.getHealthcare().getHcworkingdays().get(0).getWorkingfrom())  ,
                                    DateTimeUtils.getDateObject(favouriteEntity.getHealthcare().getHcworkingdays().get(0).getWorkingto())    );

                            if( isBetweenInterval){
                                favouriteEntity1.setState(isBetweenInterval);
                                favouriteEntity1.setVerbalState("يغلق");
                                favouriteEntity1.setClosingHour(  DateTimeUtils.getHour(favouriteEntity.getHealthcare().getHcworkingdays().get(0).getWorkingto())  );
                                favouriteEntity1.setOpeningHour(null);
                            }else{
                                favouriteEntity1.setState(isBetweenInterval);
                                favouriteEntity1.setVerbalState("يفتح");
                                favouriteEntity1.setClosingHour(  null  );
                                favouriteEntity1.setOpeningHour(DateTimeUtils.getHour(favouriteEntity.getHealthcare().getHcworkingdays().get(0).getWorkingfrom()));

                            }


                        }else {favouriteEntity1.setState(false);}

                        favouriteEntity1.setName(favouriteEntity.getHealthcare().getName());
                        favouriteEntity1.setHosType(favouriteEntity.getHealthcare().getHealthcaretype().getName());
                        favouriteEntity1.setHosTypeid(favouriteEntity.getHealthcare().getHealthcaretype_id());
                        favouriteEntity1.setTime("0.0");
                        favouriteEntity1.setDistance(LocationUtils.getDistance(currlat , currlng ,lati
                                ,longi));
                        favouriteEntity1.setAdress(favouriteEntity.getHealthcare().getAddress() );
                    /*    if (favouriteEntity.getOpening_hours()!=null){
                            favouriteEntity1.setState(favouriteEntity.getOpening_hours().isOpen_now());
                        }else {favouriteEntity1.setState(false);}*/

                       // if (){ }
                            favouriteEntity1.setImgUrl( favouriteEntity.getHealthcare().getCoverphoto());

                        favouriteEntity1.setGoogleItem(false);


                        favouriteEntity1.setVerbalState("");
                        favouriteEntity1.setIconurl(favouriteEntity.getHealthcare().getMainphoto());

                        return favouriteEntity1;
                    }
                }).toList();
    }

    public Observable<AddToFavouritesResponse> addToFavourites(Integer userId , Integer hcId ) {
        return userApi .addToFavourites(userId,hcId);
        }
        public Observable<AddToFavouritesResponse> removeFromFavourites(Integer userId  )   {
        return userApi .removeFromFavourites(userId);
        }
}
