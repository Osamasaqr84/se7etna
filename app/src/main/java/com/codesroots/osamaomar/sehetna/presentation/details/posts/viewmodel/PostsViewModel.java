package com.codesroots.osamaomar.sehetna.presentation.details.posts.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.codesroots.osamaomar.sehetna.Entities.AddLike;
import com.codesroots.osamaomar.sehetna.Entities.AddReplyResponse;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.data.remote.ApiClient;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import com.codesroots.osamaomar.sehetna.data.repositories.MainRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {

    public Integer getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Integer currentItem) {
        this.currentItem = currentItem;
    }

    Integer currentItem = 0;

    public Integer getCurrentDeleteItem() {
        return currentDeleteItem;
    }

    public void setCurrentDeleteItem(Integer currentDeleteItem) {
        this.currentDeleteItem = currentDeleteItem;
    }

    Integer currentDeleteItem = 0;
    private MutableLiveData<String> gotoDetails = new MutableLiveData<>();

    public MainRepository repository = new MainRepository( ApiClient.getClient().create(ApiInterface.class));
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<Post>> getPostsLD() {
        return postsLD;
    }

    private MutableLiveData<List<Post>> postsLD = new MutableLiveData<>();

    public MutableLiveData<Boolean> getAddedLike() {
        return addedLike;
    }

    public MutableLiveData<AddReplyResponse.PostreplyBean> getAddedReply() {
        return addedReply;
    }

    private MutableLiveData<Boolean> addedLike = new MutableLiveData<>();
    private MutableLiveData<AddReplyResponse.PostreplyBean> addedReply = new MutableLiveData<>();

    public MutableLiveData<List<CenterPostsResponse.DataBean.PostrepliesBean>> getReplies() {
        return replies;
    }


    public MutableLiveData<Boolean> getRemovedLike() {
        return removedLike;
    }

    public void setRemovedLike(MutableLiveData<Boolean> removedLike) {
        this.removedLike = removedLike;
    }

    private MutableLiveData<Boolean> removedLike = new MutableLiveData<>();

    private MutableLiveData<List<CenterPostsResponse.DataBean.PostrepliesBean>> replies = new MutableLiveData<>();

    public void  getCenterPosts(Integer page  , Integer id ,int user_id){
        mCompositeDisposable.add(
                repository.getCenterPosts(page, id ,user_id)
                        .subscribeOn(Schedulers.io())
                        /*           .doOnSubscribe({  progress.postValue(Pair(0,0)) })
                         .doFinally({ progress.postValue(Pair(0,8))  })*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postPostsResponse,
                                this::postError));

    }

    private void postPostsResponse(/*List<Post>*/ List<Post> posts) {
        // Log.d("centerposts" , posts.get(0).toString());
        postsLD.postValue(posts);
    }

    public void  addLike(int user_id,int post_id){

        mCompositeDisposable.add(
                repository.addLike(user_id, post_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postLikeResponse,
                                this::postError));
    }

    private void postLikeResponse(AddLike addLike) {
        if (addLike.getLikeid() > 0)
            addedLike.postValue(true);
        else
            addedLike.postValue(false);
    }

    
    public void addReply(int user_id,String reply,int hcpost_id){
        mCompositeDisposable.add(
                repository.addReply(user_id, reply, hcpost_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postReplyResponse,this::postError));
    }

    private void postReplyResponse(AddReplyResponse addReplyResponse) {
        addedReply.postValue(addReplyResponse.getPostreply().get(0));
    }

    public void  getPostReplies( Integer id){
        mCompositeDisposable.add(
                repository.getPostReplies( id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postrepliesResponse,
                                this::postError));

    }

    private void postrepliesResponse(List<CenterPostsResponse.DataBean.PostrepliesBean> postrepliesBeans) {
        replies.postValue(postrepliesBeans);
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

    private void postError(Throwable throwable) {
        Log.d("error",throwable.toString());
    }
    
}
