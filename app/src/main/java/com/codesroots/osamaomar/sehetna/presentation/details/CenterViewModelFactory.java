package com.codesroots.osamaomar.sehetna.presentation.details;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;


public class CenterViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public CenterViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == CenterViewModel.class)
        {
            return (T) new CenterViewModel(getApiService());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
