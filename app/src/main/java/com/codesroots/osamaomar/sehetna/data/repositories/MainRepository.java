package com.codesroots.osamaomar.sehetna.data.repositories;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.codesroots.osamaomar.sehetna.Entities.AddLike;
import com.codesroots.osamaomar.sehetna.Entities.AddReplyResponse;
import com.codesroots.osamaomar.sehetna.Entities.Categories;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.Entities.CentersResponse;
import com.codesroots.osamaomar.sehetna.Entities.Post;
import com.codesroots.osamaomar.sehetna.Entities.PostRepliesResponse;
import com.codesroots.osamaomar.sehetna.Entities.Posts;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.Utils.DateTimeUtils;
import com.codesroots.osamaomar.sehetna.Utils.LocationUtils;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class MainRepository {

    private ApiInterface userApi;
    private int user_id = PreferenceHelper.getUserId();

    public MainRepository(ApiInterface userApi) {
        this.userApi = userApi;
    }

    public Observable<Categories> getCategories() {
        return userApi.getCategories();
    }

    public Single<List<Post>> getPosts(Integer page, int user_id) {
        return userApi.getPosts(page, user_id)
                .flatMapIterable(list -> list.getPosts())
                .map(new Function<Posts.PostsBean, Post>() {
                    @Override
                    public Post apply(Posts.PostsBean postsBean) throws Exception {
                        Post post = new Post();
                        if (postsBean.getHcphotos().size() > 0) {
                            List<String> images = Stream.of(postsBean.getHcphotos())
                                    .map(Posts.PostsBean.HcphotosBean::getPhoto)
                                    .collect(Collectors.toList());
                            post.setImages(images);
                        } else {
                            post.setImages(null);
                        }
                        post.setOwner_name(postsBean.getHealthcare().getName());
                        post.setPostText(postsBean.getDescription());
                        post.setTime(postsBean.getCreated());
                        post.setOwner_image(postsBean.getHealthcare().getMainphoto());
                        if (postsBean.getHealthcare().getHccare_evaluations().size() > 0) {
                            post.setRateCount(postsBean.getHealthcare().getHccare_evaluations().get(0).getSum());
                            post.setRating(Float.valueOf(postsBean.getHealthcare().getHccare_evaluations().get(0).getCount()));
                        }
                        post.setPostId(postsBean.getId());
                        post.setLikeCount(postsBean.getTotal_like());
                        if (postsBean.getPostlikes() != null && postsBean.getPostlikes().size() > 0) {
                            // post.setPostlikesBeanList(postsBean.getPostlikes());
                        }
                        if (postsBean.getUserlikes() != null && postsBean.getUserlikes().size() > 0) {
                            post.setLiked(true);
                            post.setLikeId(postsBean.getUserlikes().get(0).getId());
                        } else {
                            post.setLikeId(0);
                            post.setLiked(false);
                        }
                        if (postsBean.getTotalpostreplies().size() > 0) {
                            post.setCommentCount(postsBean.getTotalpostreplies().get(0).getTotal_comment());
                        } else {
                            post.setCommentCount(0);
                        }

                        post.getSearchEntity().setGoogleItem(false);
                        post.getSearchEntity().setLat(postsBean.getHealthcare().getLat());
                        post.getSearchEntity().setLng(postsBean.getHealthcare().getLon());
                        post.getSearchEntity().setId(postsBean.getHealthcare().getId());
                        post.getSearchEntity().setWebsite(postsBean.getHealthcare().getWebsite());
                        post.getSearchEntity().setAdress(postsBean.getHealthcare().getAddress());
                        post.getSearchEntity().setPhoneNumber(postsBean.getHealthcare().getPhone1());
                        post.getSearchEntity().setCoverPhoto(postsBean.getHealthcare().getMainphoto());
                        post.getSearchEntity().setImgUrl(postsBean.getHealthcare().getCoverphoto());

                        if (postsBean.getHealthcare().getHcworkingdays() != null && postsBean.getHealthcare().getHcworkingdays().size() > 0) {

                            boolean isBetweenInterval = DateTimeUtils.isNowBetweenDateTime(
                                    DateTimeUtils.getDateObject(postsBean.getHealthcare().getHcworkingdays().get(0).getWorkingfrom()),
                                    DateTimeUtils.getDateObject(postsBean.getHealthcare().getHcworkingdays().get(0).getWorkingto()));

                            if (isBetweenInterval) {
                                post.getSearchEntity().setState(isBetweenInterval);
                                post.getSearchEntity().setVerbalState("يغلق");
                                post.getSearchEntity().setClosingHour(DateTimeUtils.getHour(postsBean.getHealthcare().getHcworkingdays().get(0).getWorkingto()));
                                post.getSearchEntity().setOpeningHour(null);
                            } else {
                                post.getSearchEntity().setState(isBetweenInterval);
                                post.getSearchEntity().setVerbalState("يفتح");
                                post.getSearchEntity().setClosingHour(null);
                                post.getSearchEntity().setOpeningHour(DateTimeUtils.getHour(postsBean.getHealthcare().getHcworkingdays().get(0).getWorkingfrom()));
                            }

                        } else {
                            post.getSearchEntity().setState(false);
                        }

                        return post;
                    }
                }).toList();
    }

    public Single<List<Post>> getCenterPosts(Integer page, Integer id, int user_id) {
        /*   Observable<CenterPostsResponse> postsResponse =*/
        return userApi.getCenterPosts(page, id, user_id)
                .flatMapIterable(data -> data.getData())
                .map(new Function<CenterPostsResponse.DataBean, Post>() {
                    @Override
                    public Post apply(CenterPostsResponse.DataBean dataBean) throws Exception {
                        Post post = new Post();
                        // Todo retrieve getHcphotos
                        if (dataBean.getHcphotos().size() > 0) {
                            List<String> images = Stream.of(dataBean.getHcphotos())
                                    .map(CenterPostsResponse.DataBean.HcphotosBean::getPhoto)
                                    .collect(Collectors.toList());
                            post.setImages(images);
                        } else {
                            post.setImages(null);
                        }
                        if (dataBean.getPostreplies().size() > 0) {

                            post.setReplies(dataBean.getPostreplies());
                        } else {
                            post.setReplies(null);
                        }


                        post.setOwner_name(dataBean.getHealthcare().getName()/*"مستشفي السلام"*/);
                        post.setPostText(dataBean.getDescription()/*"j48985"*/);
                        post.setTime(dataBean.getCreated()/*"2019-03-07T14:15:41+0000"*/);
                        post.setOwner_image(dataBean.getHealthcare().getMainphoto()/*"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png"*/);
                        post.setPostId(dataBean.getId());
                        post.setLikeCount(dataBean.getTotal_likes());
                        if (dataBean.getUserlikes() != null && dataBean.getUserlikes().size() > 0) {

                            post.setLiked(true);
                            post.setLikeId(dataBean.getUserlikes().get(0).getId());
                        } else {
                            post.setLikeId(0);
                            post.setLiked(false);
                        }

                        if (dataBean.getTotalpostreplies().size() > 0) {
                            post.setCommentCount(dataBean.getTotalpostreplies().get(0).getTotal_comment());
                        } else {
                            post.setCommentCount(0);
                        }

                        if (dataBean.getPostlikes() != null && dataBean.getPostlikes().size() > 0) {

                            post.setPostlikesBeanList(dataBean.getPostlikes());
                        }
                        return post;
                    }
                }).toList();
    }


    public Observable<AddLike> addLike(int user_id,int post_id) {
        return userApi.addlike(user_id, post_id);
    }

    public Observable<AddLike> removeLike(int user_id,int post_id) {
        return userApi.addlike(user_id, post_id);
    }

    public Observable<AddReplyResponse> addReply(int user_id,String reply,int hcpost_id) {
        return userApi.addReply(user_id, reply, hcpost_id);
          /*      .flatMapIterable(data -> data.getData().get(0).getHcphotos())
         .toList();*/
    }

    public Single<List<CenterPostsResponse.DataBean.PostrepliesBean>> getPostReplies(int post_id) {
        return userApi.getPostReplies(post_id).flatMapIterable(data -> data.getData())
                .map(new Function<PostRepliesResponse.ReplyBean, CenterPostsResponse.DataBean.PostrepliesBean>() {
                    @Override
                    public CenterPostsResponse.DataBean.PostrepliesBean apply(PostRepliesResponse.ReplyBean replyBean) throws Exception {
                        CenterPostsResponse.DataBean.PostrepliesBean newRate = new CenterPostsResponse.DataBean.PostrepliesBean();
                        newRate.setReply(replyBean.getReply());
                        newRate.setCreated(replyBean.getCreated());
                        newRate.setUser(replyBean.getUser());
                        return newRate;
                    }
                }).toList();
    }

    public Single<List<SearchEntity>> getCenters(int hc_id, int page,
                                                 int type, Double lat, Double lng) {

        return userApi.getCenters(hc_id, user_id, page, type).flatMapIterable(data -> data.getPosts())
                .map(new Function<CentersResponse.PostsBean, SearchEntity>() {
                    @Override
                    public SearchEntity apply(CentersResponse.PostsBean centerBean) throws Exception {

                        SearchEntity searchEntity1 = new SearchEntity();
                        searchEntity1.setLat(centerBean.getLat());
                        searchEntity1.setLng(centerBean.getLon());
                        searchEntity1.setName(centerBean.getName());
                        if (centerBean.getFavourites().size() > 0) {
                            searchEntity1.setFavourited(true);
                            searchEntity1.setFavid(centerBean.getFavourites().get(0).getId());
                        } else
                            searchEntity1.setFavourited(false);

                        searchEntity1.setSpecialist(centerBean.getSpecialization());
                        searchEntity1.setHosType("");// Todo add to backend
                        searchEntity1.setHosTypeid(centerBean.getHealthcaretype_id());
                        searchEntity1.setTime("0.0");
                        searchEntity1.setDistance(LocationUtils.getDistance(lat, lng, centerBean.getLat()
                                , centerBean.getLon()));
                        searchEntity1.setAdress(centerBean.getAddress());

                        if (centerBean.getHcworkingdays() != null && centerBean.getHcworkingdays().size() > 0) {

                            boolean isBetweenInterval = DateTimeUtils.isNowBetweenDateTime(
                                    DateTimeUtils.getDateObject(centerBean.getHcworkingdays().get(0).getWorkingfrom()),
                                    DateTimeUtils.getDateObject(centerBean.getHcworkingdays().get(0).getWorkingto()));

                            if (isBetweenInterval) {
                                searchEntity1.setState(isBetweenInterval);
                                searchEntity1.setVerbalState("يغلق");
                                searchEntity1.setClosingHour(DateTimeUtils.getHour(centerBean.getHcworkingdays().get(0).getWorkingto()));
                                searchEntity1.setOpeningHour(null);
                            } else {
                                searchEntity1.setState(isBetweenInterval);
                                searchEntity1.setVerbalState("يفتح");
                                searchEntity1.setClosingHour(null);
                                searchEntity1.setOpeningHour(DateTimeUtils.getHour(centerBean.getHcworkingdays().get(0).getWorkingfrom()));
                            }

                        } else {
                            searchEntity1.setState(false);
                        }

                        if (centerBean.getHccare_evaluations() != null && centerBean.getHccare_evaluations().size() > 0) {
                            searchEntity1.setRate_count(Math.round(centerBean.getHccare_evaluations().get(0).getSum()));
                            searchEntity1.setRating(Float.valueOf(Math.round(centerBean.getHccare_evaluations().get(0).getCount())));
                        }
                        searchEntity1.setGoogleItem(false);
                        searchEntity1.setId(centerBean.getId());
                        searchEntity1.setWebsite(centerBean.getWebsite());
                        searchEntity1.setPhoneNumber(centerBean.getPhone1());
                        searchEntity1.setIconurl(centerBean.getMainphoto());
                        searchEntity1.setCoverPhoto(centerBean.getCoverphoto());
                        return searchEntity1;
                    }
                }).toList();

    }

}
