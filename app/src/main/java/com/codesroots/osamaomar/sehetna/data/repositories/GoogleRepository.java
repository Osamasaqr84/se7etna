package com.codesroots.osamaomar.sehetna.data.repositories;

import com.codesroots.osamaomar.sehetna.Entities.GoogleSearchEntity;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;

import io.reactivex.Observable;

public class GoogleRepository {

    private ApiInterface userApi;


    public GoogleRepository(ApiInterface userApi) {
        this.userApi = userApi;

    }


    public Observable<GoogleSearchEntity> getGoogleData(String key ,
                                                        Double lat, Double lng,
                                                        String type   ,
                                                        String pagetoken)   {


        return userApi .getStoresfroomgooglesData(key, lat.toString()+","+lng.toString(), type ,pagetoken);


    }


}
