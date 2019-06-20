package com.codesroots.osamaomar.sehetna.presentation.morefragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;

import io.reactivex.disposables.CompositeDisposable;


public class MenuViewModel extends ViewModel {

    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private com.codesroots.osamaomar.sehetna.data.remote.ApiInterface ApiInterface;


    public MenuViewModel(ApiInterface apiService) {
        ApiInterface = apiService;
    }



    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }


}
