package com.codesroots.osamaomar.sehetna.presentation.details.photos;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.codesroots.osamaomar.sehetna.Entities.PhotosResponse;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.RatingRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PhotosViewModel extends ViewModel {



    private MutableLiveData<String> gotoDetails = new MutableLiveData<>();

    public RatingRepository repository = new RatingRepository( ApiClient.getClient().create(ApiInterface.class));
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<PhotosResponse.DataBean.HcphotosBean>> getPostsLD() {
        return postsLD;
    }

    private MutableLiveData<List<PhotosResponse.DataBean.HcphotosBean>> postsLD = new MutableLiveData<>();

    public void  getPhotos(Integer page  , Integer id){
        mCompositeDisposable.add(
                repository.getPhotos(page, id)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postPostsResponse,
                                this::postError));

    }

    private void postPostsResponse(/*List<Post>*/ PhotosResponse hcphotosBeanList) {
        Log.d("photos" , hcphotosBeanList.getData().get(0).getHcphotos().toString());

       postsLD.postValue(hcphotosBeanList.getData().get(0).getHcphotos());
    }

    private void postError(Throwable throwable) {
    }

}
