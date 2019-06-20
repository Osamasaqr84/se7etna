package com.codesroots.osamaomar.sehetna.presentation.Favourites;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.sehetna.Entities.AddToFavouritesResponse;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.FavouritesRepository;
import com.codesroots.osamaomar.sehetna.data.repositories.GoogleRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavouritesViewModel extends ViewModel {

    Double currlat, currlng = 0.0;
    String nextToken = "";
    String catText = "";
    String catType = "";
    String state = "notfiltered";
    String mapKey = "AIzaSyDPa2J9AKnxyYTcKTUzH3rmFqiXTIezXuM";
    Integer currentPage = 1;
    Integer serverPage = 1;

    public Integer getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Integer currentItem) {
        this.currentItem = currentItem;
    }

    Integer currentItem = 1;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Double getCurrlat() {
        return currlat;
    }

    public void setCurrlat(Double currlat) {
        this.currlat = currlat;
    }

    public Double getCurrlng() {
        return currlng;
    }

    public void setCurrlng(Double currlng) {
        this.currlng = currlng;
    }


    public String getType() {
        return catType;
    }

    public void setType(String type) {
        this.catType = type;
    }


    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Integer> categoryChoosed = new MutableLiveData<>();
    private MutableLiveData<Integer> currentDataSource = new MutableLiveData<>();

    public MutableLiveData<String> getSearchText() {
        return searchText;
    }

    private MutableLiveData<String> searchText = new MutableLiveData<>();

    public MutableLiveData<SearchEntity> getGotoDetails() {
        return gotoDetails;
    }

    private MutableLiveData<SearchEntity> gotoDetails = new MutableLiveData<>();

    public MutableLiveData<Integer> getcategoryChoosed() {
        return categoryChoosed;
    }

    public FavouritesRepository repository = new FavouritesRepository(ApiClient.getClient().create(ApiInterface.class));
    public GoogleRepository googleRepository = new GoogleRepository(ApiClient.getClientForGoogle().create(ApiInterface.class));
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<SearchEntity>> getNewData() {
        return newData;
    }

    private MutableLiveData<List<SearchEntity>> newData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getremovedFromFavourites() {
        return removedFromFavourites;
    }

    private MutableLiveData<Boolean> removedFromFavourites = new MutableLiveData<>();

    public FavouritesViewModel() {
    }

    void getData(int page,int userId, Double lat, Double lng) {
        mCompositeDisposable.add(
                repository.getFavourites(page,userId, lat, lng)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postFavourites,
                                this::postError));
    }


    private void postFavourites(List<SearchEntity> entities) {
        newData.postValue(entities);
    }

    private void postError(Throwable throwable) {
    }


    public void removeFromFavourites(Integer FavId) {
        mCompositeDisposable.add(
                repository.removeFromFavourites(FavId)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postAdd,
                                this::postError));
    }

    private void postAdd(AddToFavouritesResponse addToFavouritesResponse) {
        removedFromFavourites.postValue(addToFavouritesResponse.isSuccess());
    }


}
