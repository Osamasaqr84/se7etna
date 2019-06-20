package com.codesroots.osamaomar.sehetna.data.remote;

import com.codesroots.osamaomar.sehetna.Entities.AddLike;
import com.codesroots.osamaomar.sehetna.Entities.AddRateResponse;
import com.codesroots.osamaomar.sehetna.Entities.AddReplyResponse;
import com.codesroots.osamaomar.sehetna.Entities.AddToFavouritesResponse;
import com.codesroots.osamaomar.sehetna.Entities.Categories;
import com.codesroots.osamaomar.sehetna.Entities.CenterPostsResponse;
import com.codesroots.osamaomar.sehetna.Entities.CentersResponse;
import com.codesroots.osamaomar.sehetna.Entities.FavouriteResponse;
import com.codesroots.osamaomar.sehetna.Entities.GoogleDetailsCenter;
import com.codesroots.osamaomar.sehetna.Entities.GoogleSearchEntity;
import com.codesroots.osamaomar.sehetna.Entities.LoginResponse;
import com.codesroots.osamaomar.sehetna.Entities.MyReservation;
import com.codesroots.osamaomar.sehetna.Entities.PhotosResponse;
import com.codesroots.osamaomar.sehetna.Entities.PostRepliesResponse;
import com.codesroots.osamaomar.sehetna.Entities.Posts;
import com.codesroots.osamaomar.sehetna.Entities.RatingResponse;
import com.codesroots.osamaomar.sehetna.Entities.Register;
import com.codesroots.osamaomar.sehetna.Entities.Reservation;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=3000&language=ar")
    @Headers("Accept: Application/json")
    Observable<GoogleSearchEntity> getStoresfroomgooglesData(
            @Query("key")     String key ,
            @Query("location")String location,
            @Query("types")   String type,
            @Query("pagetoken")   String pagetoken
    );


    @GET("Healthcaretypes/gethctypes.json")
    Observable<Categories> getCategories(
    );


    @GET("Hcposts/gethcposts/{page}/{userId}.json")
    Observable<Posts> getPosts(
            @Path(value = "page", encoded = true) int dept_id,
            @Path(value = "userId", encoded = true) int userId
    );


    @GET("HccareEvaluations/getcareevaluation/{helthcare_id}.json")
    Observable<RatingResponse> getCenterEvaluation(
                @Path(value = "helthcare_id", encoded = true) int helthcare_id
    );


    @GET("https://maps.googleapis.com/maps/api/place/details/json?" +
            "fields=name,rating,vicinity,opening_hours,website,review,photo,formatted_address,geometry,formatted_phone_number")
    Observable<GoogleDetailsCenter> getGoogleCenterInfo(
            @Query("placeid")String placeid ,
            @Query("key")String location
    );




    @GET("Hcposts/gethealthcarepost/{page}/{id}/{userId}.json")
    Observable<CenterPostsResponse> getCenterPosts(
            @Path(value = "page", encoded = true) int dept_id ,
            @Path(value = "id", encoded = true) int id,
            @Path(value = "userId", encoded = true) int userId
    );


    @GET("Favourites/getuserfavourites/{userId}.json")
    Observable<FavouriteResponse> getfavourites(
            @Path(value = "userId", encoded = true) int userId
    );

    @GET("Healthcares/gethealthphotos/{page}/{id}.json")
    Observable<PhotosResponse> getPhotos(
            @Path(value = "page", encoded = true) int dept_id ,
            @Path(value = "id", encoded = true) int id
    );


    @POST("Favourites/delete/{id}.json")
    Observable<AddToFavouritesResponse> removeFromFavourites(
             @Path(value = "id", encoded = true) int id
    );


    @FormUrlEncoded
    @POST("Favourites/addfavourite.json")
    Observable<AddToFavouritesResponse> addToFavourites(
            @Field("user_id") int user_id,
            @Field("healthcare_id") int healthcare_id
    );

    @FormUrlEncoded
    @POST("postlikes/addlike.json")
    Observable<AddLike> addlike(
            @Field("user_id") int user_id,@Field("hcpost_id") int healthcare_id
    );

//    @POST("Postlikes/delete/{id}.json")
//    Observable<AddToFavouritesResponse> removeLike(
//            @Path(value = "id", encoded = true) int id
//    );

    @FormUrlEncoded
    @POST("HccareEvaluations/addhealthcareevaluations.json")
    Observable<AddRateResponse> addRate(
            @Field("user_id")       int user_id,
            @Field("rate")          int rate,
            @Field("comment")       String comment,
            @Field("healthcare_id") int healthcare_id
    );


    @FormUrlEncoded
    @POST("Healthcares/gethcdetails/{type}/{page}.json")
    Observable<CentersResponse> getCenters(
            @Field("id") int hc_id ,
            @Field("user_id") int user_id ,
            @Path(value = "page", encoded = true) int page ,
            @Path(value = "type", encoded = true)  int type
    );


    @FormUrlEncoded
    @POST("postreplies/addreplies.json")
    Observable<AddReplyResponse> addReply(
            @Field("user_id")  int user_id  ,
            @Field("reply")    String reply    ,
            @Field("hcpost_id")    int hcpost_id
    );

    @GET("postreplies/getpostreplies/{hcpost_id}.json")
    Observable<PostRepliesResponse> getPostReplies(
            @Path(value = "hcpost_id", encoded = true) int dept_id
    );

    @Multipart
    @POST("users/add.json")
    Observable<Register> userregister(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody email,
            @Part("email_verified") RequestBody email_verified,
            @Part("active") RequestBody active,
            @Part("user_group_id") RequestBody user_group_id,
            @Part MultipartBody.Part photo
            );

    @FormUrlEncoded
    @POST("users/token.json")
    Observable<LoginResponse> userlogin(
            @Field("username") String  username,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("reservations/addreservation.json")
    Observable<Reservation> reservation(
            @Field("healthcare_id") int   healthcare_id,
            @Field("reservation_date") String reservation_date,
            @Field("user_id") int user_id
    );

    @GET("reservations/getmyreservation/{userid}.json")
    Observable<MyReservation> getUserReservation(
            @Path(value = "userid", encoded = true) int userid
    );

}
