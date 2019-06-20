package com.codesroots.osamaomar.sehetna.presentation.mainfragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.sehetna.Entities.AddLike;
import com.codesroots.osamaomar.sehetna.Entities.Categories;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.MainRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    Double currlat, currlng = 0.0;
    Integer currentPage = 1;
    Integer serverPage = 1;
    String nextToken = "", catText = "", catType = "";
    String mapKey = "AIzaSyDPa2J9AKnxyYTcKTUzH3rmFqiXTIezXuM";


    public Integer getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Integer currentItem) {
        this.currentItem = currentItem;
    }

    Integer currentItem = 0;

//    public Integer getCurrentDeleteItem() {
//        return currentDeleteItem;
//    }
//
//    public void setCurrentDeleteItem(Integer currentDeleteItem) {
//        this.currentDeleteItem = currentDeleteItem;
//    }
//
//    Integer currentDeleteItem = 0;


    public MutableLiveData<Boolean> getAddedLike() {
        return addedLike;
    }

    private MutableLiveData<Boolean> addedLike = new MutableLiveData<>();

    public MutableLiveData<Boolean> getRemovedLike() {
        return removedLike;
    }

    public void setRemovedLike(MutableLiveData<Boolean> removedLike) {
        this.removedLike = removedLike;
    }

    private MutableLiveData<Boolean> removedLike = new MutableLiveData<>();


    public MainRepository repository = new MainRepository(ApiClient.getClient().create(ApiInterface.class));

    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<SearchEntity> getGotoDetails() {
        return gotoDetails;
    }

    private MutableLiveData<SearchEntity> gotoDetails = new MutableLiveData<>();


    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<Categories.Types>> getNewData() {
        return categoriesLD;
    }

    public MutableLiveData<List<Categories.Types>> getCategoriesLD() {
        return categoriesLD;
    }

    private MutableLiveData<List<Categories.Types>> categoriesLD = new MutableLiveData<>();

    public MutableLiveData<List<Post>> getPostsLD() {
        return postsLD;
    }

    private MutableLiveData<List<Post>> postsLD = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    public MainViewModel() {
    }

    void getCategories() {
        mCompositeDisposable.add(
                repository.getCategories()
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postCategoryResponse,
                                this::postError));

    }

    private void postCategoryResponse(Categories categories) {
        categoriesLD.postValue(categories.getTypes());
    }

    private void postError(Throwable throwable) {
        error.postValue(throwable);
    }

    void getPosts(Integer page, int user_id) {
        mCompositeDisposable.add(
                repository.getPosts(page, user_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postPostsResponse,
                                this::postError));
    }

    private void postPostsResponse(List<Post> posts) {
        postsLD.postValue(posts);
    }

    public void addLike(int user_id,int post_id) {
        mCompositeDisposable.add(
                repository.addLike(user_id, post_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postLikeResponse,
                                this::postError));
    }

    public void  removeLike(int userid,int postid){
        mCompositeDisposable.add(
                repository.removeLike(userid,postid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postRemoveLikeResponse,
                                this::postError));
    }

    private void postRemoveLikeResponse(AddLike addLike) {
        if (addLike.getLikeid() > 0)
            removedLike.postValue(true);
        else
            removedLike.postValue(false);
    }

    private void postLikeResponse(AddLike addLike) {
        if (addLike.getLikeid() > 0)
            addedLike.postValue(true);
        else
            addedLike.postValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
