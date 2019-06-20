package com.codesroots.osamaomar.sehetna.presentation.details.ratesFragmenr;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.codesroots.osamaomar.sehetna.Entities.AddRateResponse;
import com.codesroots.osamaomar.sehetna.Entities.RatingResponse;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.RatingRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RatesViewModel extends ViewModel {

    private MutableLiveData<String> gotoDetails = new MutableLiveData<>();

    public RatingRepository repository = new RatingRepository( ApiClient.getClient().create(ApiInterface.class));
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<RatingResponse.DataBean>> getPostsLD() {
        return postsLD;
    }

    private MutableLiveData<List<RatingResponse.DataBean>> postsLD = new MutableLiveData<>();

    public MutableLiveData<AddRateResponse> getAddRateLD() {
        return addRateLD;
    }

    private MutableLiveData<AddRateResponse> addRateLD = new MutableLiveData<>();

    public void  getCenterEvaluation( Integer id){
        mCompositeDisposable.add(
                repository.getCenterEvaluation( id)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postPostsResponse,
                                this::postError));

    }

    private void postPostsResponse(/*List<Post>*/ List<RatingResponse.DataBean> rates) {
        Log.d("centerposts" , rates.toString());

        postsLD.postValue(rates);
    }

    private void postError(Throwable throwable) {
    }



    public void  addCenterEvaluation( int user_id,
                                      int rate,
                                      String comment,
                                      int healthcare_id){
        mCompositeDisposable.add(
                repository.addRate( user_id, rate, comment, healthcare_id)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postAddRateResponse,
                                this::postError));

    }

    private void postAddRateResponse(AddRateResponse addRateResponse) {
        addRateLD.postValue(addRateResponse);

    }


}
