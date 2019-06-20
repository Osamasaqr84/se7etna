package com.codesroots.osamaomar.sehetna.presentation.Search.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import android.util.Log;
import android.util.Pair;

import com.codesroots.osamaomar.sehetna.Entities.AddToFavouritesResponse;
import com.codesroots.osamaomar.sehetna.Entities.GoogleSearchEntity;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.FavouritesRepository;
import com.codesroots.osamaomar.sehetna.data.repositories.GoogleRepository;
import com.codesroots.osamaomar.sehetna.data.repositories.MainRepository;
import com.codesroots.osamaomar.sehetna.Utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ResultViewModel extends ViewModel {

    Double currlat, currlng = 0.0;
    String nextToken = "";
    String catText = "";

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    String catType = "";

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    Integer typeId = 0;
    String state = "notfiltered";

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    String mapKey = "";
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

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public String getCatText() {
        return catText;
    }

    public void setCatText(String catText) {
        this.catText = catText;
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


    public MutableLiveData<Pair<String, Integer>> getBottomSheetState() {
        return bottomSheetState;
    }

    private MutableLiveData<Pair<String, Integer>> bottomSheetState = new MutableLiveData<>();


    public MutableLiveData<Integer> getcategoryChoosed() {
        return categoryChoosed;
    }

    public MainRepository repository = new MainRepository(ApiClient.getClient().create(ApiInterface.class));
    public FavouritesRepository favouritesRepository = new FavouritesRepository(ApiClient.getClient().create(ApiInterface.class));
    public GoogleRepository googleRepository = new GoogleRepository(ApiClient.getClientForGoogle().create(ApiInterface.class));

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<SearchEntity>> getNewData() {
        return newData;
    }

    private MutableLiveData<List<SearchEntity>> newData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getAddedToFavourites() {
        return addedToFavourites;
    }

    private MutableLiveData<Boolean> addedToFavourites = new MutableLiveData<>();

    public ResultViewModel() {
    }

    public void getData() {
        if (state.equals("filtered")) return;
        if (currentPage % 2 == 0) {
            getGoogleData(mapKey, currlat, currlng, catType, nextToken);

        } else if (currentPage % 2 == 1) {
            getServerData(typeId, serverPage, 1, currlat, currlng);
        }
    }

    public void addToFavourites(Integer userId, Integer hcId) {
        mCompositeDisposable.add(
                favouritesRepository.addToFavourites(userId, hcId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postAdd,
                                this::postError));

    }

    private void postAdd(AddToFavouritesResponse addToFavouritesResponse) {
        addedToFavourites.postValue(addToFavouritesResponse.isSuccess());
    }

    private void postError(Throwable throwable) {
        Log.d("errors", throwable.toString());

    }

    public void getGoogleData(String key,
                              Double lat, Double lng,
                              String type, String pagetoken) {
        currlat = lat;
        currlng = lng;
        catType = type;

        mCompositeDisposable.add(
                googleRepository.getGoogleData(key, lat, lng, type, pagetoken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postGoogleResponse,
                                this::postError));
    }

    private void postGoogleResponse(GoogleSearchEntity entity) {

        nextToken = entity.getNext_page_token();
        List<SearchEntity> entities = new ArrayList<>();

        for (GoogleSearchEntity.ResultsBean searchEntity : entity.getResults()) {

            SearchEntity searchEntity1 = new SearchEntity();
            searchEntity1.setPlace_Id(searchEntity.getPlace_id());
            searchEntity1.setId(0);
            searchEntity1.setLat(searchEntity.getGeometry().getLocation().getLat());
            searchEntity1.setLng(searchEntity.getGeometry().getLocation().getLng());
            searchEntity1.setRating(Float.valueOf(Math.round(searchEntity.getRating())));
            searchEntity1.setName(searchEntity.getName());
            searchEntity1.setHosType(searchEntity.getTypes().get(0));
            searchEntity1.setTime("0.0");
            searchEntity1.setDistance(LocationUtils.getDistance(currlat, currlng, searchEntity.getGeometry().getLocation().getLat()
                    , searchEntity.getGeometry().getLocation().getLng()));
            searchEntity1.setAdress(searchEntity.getVicinity());
            if (searchEntity.getOpening_hours() != null) {
                searchEntity1.setState(searchEntity.getOpening_hours().isOpen_now());
            } else {
                searchEntity1.setState(false);
            }

            if (searchEntity.getPhotos() != null) {
                searchEntity1.setImgUrl(searchEntity.getPhotos().get(0).getPhoto_reference());
            }
            searchEntity1.setGoogleItem(true);
            searchEntity1.setId(searchEntity.getId());
            searchEntity1.setRate_count(searchEntity.getUser_ratings_total());
            searchEntity1.setVerbalState("");
            searchEntity1.setIconurl(searchEntity.getIcon());
            //   searchEntity1.setPhoneNumber(searchEntity.gep);

            entities.add(searchEntity1);
        }

        newData.postValue(entities);
    }


    public void getServerData(int hc_id, int page,
                              int type, Double lat, Double lng) {
        mCompositeDisposable.add(
                repository.getCenters(hc_id, page, type, lat, lng)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postServerResponse,
                                this::postError));
    }

    private void postServerResponse(List<SearchEntity> searchEntities) {
        serverPage++;
        newData.postValue(searchEntities);
    }

}
