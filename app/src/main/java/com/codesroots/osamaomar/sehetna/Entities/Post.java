package com.codesroots.osamaomar.sehetna.Entities;

import java.util.List;

public class Post {
    String owner_name  , postText  , time , owner_image;
    Float rating = Float.valueOf(0);
    List<String> images ;
    Integer rateCount =0 ;
    Integer likeCount = 0;
    Integer commentCount = 0 ;

    private SearchEntity searchEntity = new SearchEntity();

    public SearchEntity getSearchEntity() {
        return searchEntity;
    }

    public void setSearchEntity(SearchEntity searchEntity) {
        this.searchEntity = searchEntity;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    Integer likeId ;
    List<CenterPostsResponse.DataBean.PostrepliesBean> replies ;
    boolean Liked ;

    public boolean isAddedLike() {
        return addedLike;
    }

    public void setAddedLike(boolean addedLike) {
        this.addedLike = addedLike;
    }

    boolean addedLike =false ;
    int postId;

    public List<CenterPostsResponse.DataBean.PostlikesBean> getPostlikesBeanList() {
        return postlikesBeanList;
    }

    public void setPostlikesBeanList(List<CenterPostsResponse.DataBean.PostlikesBean> postlikesBeanList) {
        this.postlikesBeanList = postlikesBeanList;
    }

    List<CenterPostsResponse.DataBean.PostlikesBean> postlikesBeanList ;

    public List<CenterPostsResponse.DataBean.PostrepliesBean> getReplies() {
        return replies;
    }

    public void setReplies(List<CenterPostsResponse.DataBean.PostrepliesBean> replies) {
        this.replies = replies;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }



    public boolean getLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }



    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }



    public Post(String owner_name, String postText, String time, String owner_image, Float rating, List<String> images, Integer rateCount) {
        this.owner_name = owner_name;
        this.postText = postText;
        this.time = time;
        this.owner_image = owner_image;
        this.rating = rating;
        this.images = images;
        this.rateCount = rateCount;
    }

    public Post() {
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner_image() {
        return owner_image;
    }

    public void setOwner_image(String owner_image) {
        this.owner_image = owner_image;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }




}
