package com.codesroots.osamaomar.sehetna.Entities;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CenterPostsResponse {



    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * total_likes : 1
         * id : 2
         * healthcare_id : 1
         * description : aaa
         * created : 2019-02-08T00:00:00+0000
         * modified : 2019-02-19T13:43:00+0000
         * healthcare : {"id":1,"name":"مستشفي السلام","mainphoto":"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png","lat":29.12345,"lon":30.12345,"hcworkingdays":[{"id":1,"healthcare_id":1,"dayname":"Saturday","workingfrom":"2019-03-11T14:00:00+0000","workingto":"2019-03-11T19:00:00+0000","created":null,"modified":null},{"id":3,"healthcare_id":1,"dayname":"Monday","workingfrom":"2019-03-11T17:00:34+0000","workingto":"2019-03-11T17:00:34+0000","created":"2019-03-04T17:00:34+0000","modified":"2019-03-04T17:00:34+0000"},{"id":4,"healthcare_id":1,"dayname":"Thursday","workingfrom":"2019-03-11T09:15:41+0000","workingto":"2019-03-11T16:15:41+0000","created":"2019-03-07T14:15:41+0000","modified":"2019-03-07T14:15:41+0000"}]}
         * postreplies : []
         * hcphotos : []
         */

        private Integer total_like;
        private int id;
        private int healthcare_id;
        private String description;
        private String created;
        private String modified;
        private HealthcareBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX healthcare;
        private List<PostrepliesBean> postreplies;
        private List<HcphotosBean> hcphotos;
        private List<TotalpostrepliesBean> totalpostreplies;
        private List<PostlikesBean> postlikes;

        private List<UserlikesBean> userlikes;

        public List<UserlikesBean> getUserlikes() {
            return userlikes;
        }

        public void setUserlikes(List<UserlikesBean> userlikes) {
            this.userlikes = userlikes;
        }

        public static class UserlikesBean {
            /**
             * id : 12
             * user_id : 1
             * hcpost_id : 66
             * created : 2019-03-11T13:05:04+0000
             * modified : 2019-03-11T13:05:04+0000
             */

            private int id;
            private int user_id;
            private int hcpost_id;
            private String created;
            private String modified;

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
        }

        public List<PostlikesBean> getPostlikes() {
            return postlikes;
        }

        public void setPostlikes(List<PostlikesBean> postlikes) {
            this.postlikes = postlikes;
        }

        public static class PostlikesBean implements Parcelable {
            /**
             * id : 2
             * user_id : 1
             * hcpost_id : 2
             * created : null
             * modified : null
             * user : {"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png","mobile":"","email":"assaas@yahoo.com"}
             */

            private int id;
            private int user_id;
            private int hcpost_id;
            private String created;
            private String modified;
            UserBean user;

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
                 * mobile :
                 * email : assaas@yahoo.com
                 */

                private int id;
                private String username;
                private String photo;
                private String mobile;
                private String email;

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

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
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
                    dest.writeString(this.mobile);
                    dest.writeString(this.email);
                }

                public UserBean() {
                }

                protected UserBean(Parcel in) {
                    this.id = in.readInt();
                    this.username = in.readString();
                    this.photo = in.readString();
                    this.mobile = in.readString();
                    this.email = in.readString();
                }

                public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.user_id);
                dest.writeInt(this.hcpost_id);
                dest.writeString(this.created);
                dest.writeString(this.modified);
                dest.writeParcelable(this.user, flags);
            }

            public PostlikesBean() {
            }

            protected PostlikesBean(Parcel in) {
                this.id = in.readInt();
                this.user_id = in.readInt();
                this.hcpost_id = in.readInt();
                this.created = in.readString();
                this.modified = in.readString();
                this.user = in.readParcelable(UserBean.class.getClassLoader());
            }

            public static final Parcelable.Creator<PostlikesBean> CREATOR = new Parcelable.Creator<PostlikesBean>() {
                @Override
                public PostlikesBean createFromParcel(Parcel source) {
                    return new PostlikesBean(source);
                }

                @Override
                public PostlikesBean[] newArray(int size) {
                    return new PostlikesBean[size];
                }
            };
        }

        public List<PostrepliesBean> getPostreplies() {
            return postreplies;
        }

        public void setPostreplies(List<PostrepliesBean> postreplies) {
            this.postreplies = postreplies;
        }

        public List<HcphotosBean> getHcphotos() {
            return hcphotos;
        }

        public void setHcphotos(List<HcphotosBean> hcphotos) {
            this.hcphotos = hcphotos;
        }

        public List<TotalpostrepliesBean> getTotalpostreplies() {
            return totalpostreplies;
        }

        public void setTotalpostreplies(List<TotalpostrepliesBean> totalpostreplies) {
            this.totalpostreplies = totalpostreplies;
        }

        public static class PostrepliesBean implements Parcelable {
            /**
             * id : 1
             * user_id : 1
             * reply : دكتورة ايمان ما شاء الله عليها
             * hcpost_id : 2
             * created : 2019-02-13T00:00:00+0000
             * modified : 2019-02-13T00:00:00+0000
             */

            private int id;
            private int user_id;
            private String reply;
            private int hcpost_id;
            private String created;
            private String modified;
            private PostRepliesResponse.ReplyBean.UserBean user;
            public PostRepliesResponse.ReplyBean.UserBean getUser() {
                return user;
            }

            public void setUser(PostRepliesResponse.ReplyBean.UserBean user) {
                this.user = user;
            }



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

            public PostrepliesBean() {
            }

            public static class UserBean implements Parcelable {
                /**
                 * id : 1
                 * username : admin
                 */

                private int id;
                private String username;
                private String photo;
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

                public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.user_id);
                dest.writeString(this.reply);
                dest.writeInt(this.hcpost_id);
                dest.writeString(this.created);
                dest.writeString(this.modified);
                dest.writeParcelable(this.user, flags);
            }

            protected PostrepliesBean(Parcel in) {
                this.id = in.readInt();
                this.user_id = in.readInt();
                this.reply = in.readString();
                this.hcpost_id = in.readInt();
                this.created = in.readString();
                this.modified = in.readString();
                this.user = in.readParcelable(PostRepliesResponse.ReplyBean.UserBean.class.getClassLoader());
            }

            public static final Creator<PostrepliesBean> CREATOR = new Creator<PostrepliesBean>() {
                @Override
                public PostrepliesBean createFromParcel(Parcel source) {
                    return new PostrepliesBean(source);
                }

                @Override
                public PostrepliesBean[] newArray(int size) {
                    return new PostrepliesBean[size];
                }
            };
        }

        public static class HcphotosBean {
            /**
             * id : 20
             * healthcare_id : 1
             * hcpost_id : 2
             * photo : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
             * created : 2019-03-11T13:46:55+0000
             * modified : 2019-03-11T13:46:55+0000
             */

            private int id;
            private int healthcare_id;
            private int hcpost_id;
            private String photo;
            private String created;
            private String modified;

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

            public int getHcpost_id() {
                return hcpost_id;
            }

            public void setHcpost_id(int hcpost_id) {
                this.hcpost_id = hcpost_id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
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
        }

        public Integer getTotal_likes() {
            return total_like;
        }

        public void setTotal_likes(Integer total_likes) {
            this.total_like = total_likes;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public HealthcareBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX getHealthcare() {
            return healthcare;
        }

        public void setHealthcare(HealthcareBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX healthcare) {
            this.healthcare = healthcare;
        }

     
        public static class HealthcareBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX {
            /**
             * id : 1
             * name : مستشفي السلام
             * mainphoto : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
             * lat : 29.12345
             * lon : 30.12345
             * hcworkingdays : [{"id":1,"healthcare_id":1,"dayname":"Saturday","workingfrom":"2019-03-11T14:00:00+0000","workingto":"2019-03-11T19:00:00+0000","created":null,"modified":null},{"id":3,"healthcare_id":1,"dayname":"Monday","workingfrom":"2019-03-11T17:00:34+0000","workingto":"2019-03-11T17:00:34+0000","created":"2019-03-04T17:00:34+0000","modified":"2019-03-04T17:00:34+0000"},{"id":4,"healthcare_id":1,"dayname":"Thursday","workingfrom":"2019-03-11T09:15:41+0000","workingto":"2019-03-11T16:15:41+0000","created":"2019-03-07T14:15:41+0000","modified":"2019-03-07T14:15:41+0000"}]
             */

            private int id;
            private String name;
            private String mainphoto;
            private double lat;
            private double lon;
            private List<HcworkingdaysBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX> hcworkingdays;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMainphoto() {
                return mainphoto;
            }

            public void setMainphoto(String mainphoto) {
                this.mainphoto = mainphoto;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public List<HcworkingdaysBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX> getHcworkingdays() {
                return hcworkingdays;
            }

            public void setHcworkingdays(List<HcworkingdaysBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX> hcworkingdays) {
                this.hcworkingdays = hcworkingdays;
            }

            public static class HcworkingdaysBeanXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX {
                /**
                 * id : 1
                 * healthcare_id : 1
                 * dayname : Saturday
                 * workingfrom : 2019-03-11T14:00:00+0000
                 * workingto : 2019-03-11T19:00:00+0000
                 * created : null
                 * modified : null
                 */

                private int id;
                private int healthcare_id;
                private String dayname;
                private String workingfrom;
                private String workingto;
                private Object created;
                private Object modified;

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

                public String getDayname() {
                    return dayname;
                }

                public void setDayname(String dayname) {
                    this.dayname = dayname;
                }

                public String getWorkingfrom() {
                    return workingfrom;
                }

                public void setWorkingfrom(String workingfrom) {
                    this.workingfrom = workingfrom;
                }

                public String getWorkingto() {
                    return workingto;
                }

                public void setWorkingto(String workingto) {
                    this.workingto = workingto;
                }

                public Object getCreated() {
                    return created;
                }

                public void setCreated(Object created) {
                    this.created = created;
                }

                public Object getModified() {
                    return modified;
                }

                public void setModified(Object modified) {
                    this.modified = modified;
                }
            }
        }

        public static class TotalpostrepliesBean {
            /**
             * hcpost_id : 2
             * total_comment : 4
             */

            private int hcpost_id;
            private int total_comment;

            public int getHcpost_id() {
                return hcpost_id;
            }

            public void setHcpost_id(int hcpost_id) {
                this.hcpost_id = hcpost_id;
            }

            public int getTotal_comment() {
                return total_comment;
            }

            public void setTotal_comment(int total_comment) {
                this.total_comment = total_comment;
            }
        }
    }
}
