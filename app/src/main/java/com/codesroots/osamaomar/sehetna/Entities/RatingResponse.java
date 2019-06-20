package com.codesroots.osamaomar.sehetna.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RatingResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable{

        public DataBean(float rate, String comment,String username,String userphoto) {
            this.rate = rate;
            this.comment = comment;
            this.user=new UserBean(username,userphoto);
        }

        /**
         * id : 1
         * healthcare_id : 1
         * user_id : 1
         * rate : 3
         * created : null
         * modified : null
         * comment : دكتورة ايمان
         * user : {"id":1,"username":"admin"}
         */

        private int id;
        private int healthcare_id;
        private int user_id;
        private float rate;
        private String created;
        private Object modified;
        private String comment;
        private UserBean user;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            id = in.readInt();
            healthcare_id = in.readInt();
            user_id = in.readInt();
            rate = in.readInt();
            created = in.readString();
            comment = in.readString();
            user = in.readParcelable(UserBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHealthcare_id() {
            return healthcare_id;
        }

        public void setHealthcare_id(int healthcare_id) {
            this.healthcare_id = healthcare_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getModified() {
            return modified;
        }

        public void setModified(Object modified) {
            this.modified = modified;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(healthcare_id);
            dest.writeInt(user_id);
            dest.writeFloat(rate);
            dest.writeString(created);
            dest.writeString(comment);
            dest.writeParcelable(user, flags);
        }

        public static class UserBean implements Parcelable {
            /**
             * id : 1
             * username : admin
             */

            private int id;
            private String username;
            private String photo;

            public UserBean(String username, String photo) {
                this.username = username;
                this.photo = photo;
            }

            protected UserBean(Parcel in) {
                id = in.readInt();
                username = in.readString();
                photo = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel in) {
                    return new UserBean(in);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(username);
                dest.writeString(photo);
            }
        }
    }
}
