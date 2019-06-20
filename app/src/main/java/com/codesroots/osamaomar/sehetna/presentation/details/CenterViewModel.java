package com.codesroots.osamaomar.sehetna.presentation.details;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.codesroots.osamaomar.sehetna.Entities.AddToFavouritesResponse;
import com.codesroots.osamaomar.sehetna.Entities.GoogleDetailsCenter;
import com.codesroots.osamaomar.sehetna.Entities.MyReservation;
import com.codesroots.osamaomar.sehetna.Entities.Reservation;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CenterViewModel extends ViewModel {

    public MutableLiveData<AddToFavouritesResponse> addToFavMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<GoogleDetailsCenter> googleDetailsCenterMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Reservation> reservationMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<MyReservation> myReservationMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddToFavouritesResponse> deleteToFavMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    ApiInterface apiInterface;

    CenterViewModel(ApiInterface ApiInterface1) {
        apiInterface = ApiInterface1;
    }

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public  void getGoogleCenterDetails (String placeid,String goglekey)
    {
        mCompositeDisposable.add(
                apiInterface.getGoogleCenterInfo(placeid,goglekey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postCenterData,this::postError));
    }


    public  void addReservation (String dateofreser,int healthcar,int userid)
    {
        mCompositeDisposable.add(
                apiInterface.reservation(healthcar,dateofreser,userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postReservationState,this::postError));
    }


    public  void getUserReservation (int userid)
    {
        mCompositeDisposable.add(
                apiInterface.getUserReservation(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postReservationState,this::postError));
    }

    private void postReservationState(MyReservation myReservation) {
        myReservationMutableLiveData.postValue(myReservation);
    }

    private void postReservationState(Reservation  reservation) {
        reservationMutableLiveData.postValue(reservation);
    }


    private void postCenterData(GoogleDetailsCenter detailsCenter) {
        googleDetailsCenterMutableLiveData.postValue(detailsCenter);
    }


    public  void AddToFav (int helthcare_id,int userid)
    {
       // getObservableToFavObservable(helthcare_id,userid).subscribeWith(getObserverAddFav());
        mCompositeDisposable.add(
                apiInterface.addToFavourites(userid,helthcare_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postAdd,this::postError));
    }

    public  void DeleteFav (int favid)
    {
        mCompositeDisposable.add(
                apiInterface.removeFromFavourites(favid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postAdd,
                                this::postError));
    }

    private void postAdd(AddToFavouritesResponse addToFavouritesResponse) {
        addToFavMutableLiveData.postValue(addToFavouritesResponse);
    }

    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }


//    //////////////// add to fav
//    @SuppressLint("CheckResult")
//    private Observable<AddToFavouritesResponse> getObservableToFavObservable(int helthcare_id, int userid) {
//        Observable<AddToFavouritesResponse> addToFavObservable = apiInterface.addToFavourites(userid,helthcare_id);
//        addToFavObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        return addToFavObservable;
//    }
//    private DisposableObserver<AddToFavouritesResponse> getObserverAddFav() {
//        return new DisposableObserver<AddToFavouritesResponse>() {
//            @Override
//            public void onNext(@NonNull AddToFavouritesResponse result) {
//                addToFavMutableLiveData.postValue(result);
//            }
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d("Errors", "Error" + e);
//                e.printStackTrace();
//                throwableMutableLiveData.postValue(e);
//            }
//            @Override
//            public void onComplete() {
//            }
//        };
//    }
//
//    ///////////// delete fav
//    @SuppressLint("CheckResult")
//    private Observable<AddToFavouritesResponse> getObservableToDeleteFav(int favid) {
//        Observable<AddToFavouritesResponse> addToFavObservable = apiInterface.removeFromFavourites(favid);
//        addToFavObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        return addToFavObservable;
//    }
//
//    private DisposableObserver<AddToFavouritesResponse> getObserverDeletFav() {
//        return new DisposableObserver<AddToFavouritesResponse>() {
//            @Override
//            public void onNext(@NonNull AddToFavouritesResponse result) {
//                deleteToFavMutableLiveData.postValue(result);
//            }
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d("Errors", "Error" + e);
//                e.printStackTrace();
//                throwableMutableLiveData.postValue(e);
//            }
//            @Override
//            public void onComplete() {
//            }
//        };
//    }


}
