package com.codesroots.osamaomar.sehetna.presentation.register;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;


public class RegisterViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;//// for getorder to this user



    public RegisterViewModelFactory(Application application1) {
        application = application1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

         if (modelClass == RegisterViewModel.class)
        {
            return (T) new RegisterViewModel(getApiService(),application);
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
