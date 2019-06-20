package com.codesroots.osamaomar.sehetna.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import io.reactivex.annotations.Nullable;

public class SearchEntity implements Parcelable {

    public SearchEntity() {
                            }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHosType() {
        return hosType;
    }

    public void setHosType(String hosType) {
        this.hosType = hosType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getVerbalState() {
        return verbalState;
    }

    public void setVerbalState(String verbalState) {
        this.verbalState = verbalState;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public SearchEntity(Double lat, Double lng, Float rating, String name, String hosType, String time, Integer distance, String adress, Boolean state, String verbalState, String imgUrl, Boolean googleItem) {
        this.rating = rating;
        this.name = name;
        this.hosType = hosType;
        this.time = time;
        this.distance = distance;
        this.adress = adress;
        this.state = state;
        this.verbalState = verbalState;
        this.imgUrl = imgUrl;
        this.lat = lat;
        this.lng = lng;
        this.googleItem = googleItem;
    }

    private Float rating;

    @Nullable
    private String name;
    @Nullable
    private String hosType;

    @Nullable
    private int hosTypeid;


    @Nullable
    private String time;
    @Nullable
    private String adress;
    @Nullable
    private String verbalState;
    @Nullable
    private String imgUrl;

    public int getHosTypeid() {
        return hosTypeid;
    }

    public void setHosTypeid(int hosTypeid) {
        this.hosTypeid = hosTypeid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Nullable
    private String website;

    @Nullable
    private String specialist;

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

    public void setId(String id) {
        Id = id;
    }

    public static Creator<SearchEntity> getCREATOR() {
        return CREATOR;
    }
    @Nullable
     String openingHour , closingHour ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    private int id;

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    @Nullable
    private String iconurl;

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    @Nullable
    private String coverPhoto;


    @Nullable
    private String phoneNumber;

    public String getID() {
        return Id;
    }

    public void setID(String id) {
        Id = id;
    }

    @Nullable
    private String Id;


    private String Place_Id;

    public String getPlace_Id() {
        return Place_Id;
    }

    public void setPlace_Id(String place_Id) {
        Place_Id = place_Id;
    }

    public Boolean getGoogleItem() {
        return googleItem;
    }

    public void setGoogleItem(Boolean googleItem) {
        this.googleItem = googleItem;
    }

    Boolean googleItem;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    Double lat, lng;
    Integer distance;

    public boolean isFavourited() {
        return Favourited;
    }

    public void setFavourited(boolean favourited) {
        Favourited = favourited;
    }

    boolean Favourited;
    int favid;

    public int getFavid() {
        return favid;
    }

    public void setFavid(int favid) {
        this.favid = favid;
    }

    public Integer getRate_count() {
        return rate_count;
    }

    public void setRate_count(Integer rate_count) {
        this.rate_count = rate_count;
    }

    Integer rate_count;

    Boolean state;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.rating);
        dest.writeString(this.name);
        dest.writeString(this.hosType);
        dest.writeString(this.website);
        dest.writeString(this.time);
        dest.writeString(this.adress);
        dest.writeString(this.verbalState);
        dest.writeString(this.imgUrl);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.Id);
        dest.writeValue(this.googleItem);
        dest.writeValue(this.lat);
        dest.writeValue(this.lng);
        dest.writeValue(this.distance);
        dest.writeValue(this.rate_count);
        dest.writeValue(this.state);
    }

    protected SearchEntity(Parcel in) {
        this.rating = (Float) in.readValue(Float.class.getClassLoader());
        this.name = in.readString();
        this.hosType = in.readString();
        this.website = in.readString();
        this.time = in.readString();
        this.adress = in.readString();
        this.verbalState = in.readString();
        this.imgUrl = in.readString();
        this.phoneNumber = in.readString();
        this.Id = in.readString();
        this.googleItem = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lng = (Double) in.readValue(Double.class.getClassLoader());
        this.distance = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rate_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.state = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchEntity> CREATOR = new Parcelable.Creator<SearchEntity>() {
        @Override
        public SearchEntity createFromParcel(Parcel source) {
            return new SearchEntity(source);
        }

        @Override
        public SearchEntity[] newArray(int size) {
            return new SearchEntity[size];
        }
    };
}
