package com.codesroots.osamaomar.sehetna.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PostRepliesResponse {


    private List<ReplyBean> data;

    public List<ReplyBean> getData() {
        return data;
    }

    public void setData(List<ReplyBean> data) {
        this.data = data;
    }

    public static class ReplyBean {
        /**
         * id : 1
         * user_id : 1
         * reply : دكتورة ايمان ما شاء الله عليها
         * hcpost_id : 2
         * created : 2019-02-13T00:00:00+0000
         * modified : 2019-02-13T00:00:00+0000
         * user : {"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png"}
         */

        private int id;
        private int user_id;
        private String reply;
        private int hcpost_id;
        private String created;
        private String modified;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public int getHcpost_id() {
            return hcpost_id;
        }

        public void setHcpost_id(int hcpost_id) {
            this.hcpost_id = hcpost_id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Parcelable {
            /**
             * id : 1
             * username : admin
             * photo : http://marakez.codesroots.com/webroot/img/15390008811521779367.png
             */

            private int id;
            private String username;
            private String photo;

            public UserBean(int id, String username, String photo) {
                this.id = id;
                this.username = username;
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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.username);
                dest.writeString(this.photo);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.id = in.readInt();
                this.username = in.readString();
                this.photo = in.readString();
            }

            public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel source) {
                    return new UserBean(source);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };
        }
    }
}
