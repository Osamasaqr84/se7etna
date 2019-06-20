package com.codesroots.osamaomar.sehetna.Entities;

import java.util.List;

public class Posts {

    private List<PostsBean> posts;

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {
        /**
         * total_like : 4
         * id : 102
         * healthcare_id : 1
         * description : asdasdasd
         * created : 2019-04-07T09:28:30+0000
         * modified : 2019-04-07T09:28:30+0000
         * totalpostreplies : [{"hcpost_id":102,"total_comment":1}]
         * postreplies : [{"id":98,"user_id":1,"reply":"سوسو","hcpost_id":102,"created":"2019-04-16T14:59:51+0000","modified":"2019-04-16T14:59:51+0000","user":{"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png"}}]
         * hcphotos : [{"id":129,"healthcare_id":1,"hcpost_id":102,"photo":"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png","created":"2019-04-16T14:58:31+0000","modified":"2019-04-16T14:58:31+0000"},{"id":130,"healthcare_id":1,"hcpost_id":102,"photo":"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png","created":"2019-04-16T14:58:31+0000","modified":"2019-04-16T14:58:31+0000"}]
         * healthcare : {"id":1,"name":"مستشفي السلام الدولي","mainphoto":"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png","created":"2019-03-12T14:17:43+0000","phone1":"hsh","phone2":"01100925778","phone3":"01151355233","address":"مستشفى السلام الدولي، Port Said Street, Bneid Al Qar, Kuwait","website":"lalalal","description":"اكتب الوصف  هنا","lat":29.3696209,"lon":48.0077714,"hccare_evaluations":[{"id":1,"user_id":1,"healthcare_id":1,"sum":74,"count":30}],"hcworkingdays":[{"id":7,"healthcare_id":1,"dayname":"Tuesday","workingfrom":"2019-04-16T10:32:19+0000","workingto":"2019-04-16T15:32:19+0000"}],"favourites":[]}
         * userlikes : [{"id":130,"user_id":57,"hcpost_id":102,"created":"2019-04-16T15:03:36+0000","modified":"2019-04-16T15:03:36+0000"}]
         * postlikes : [{"id":115,"user_id":1,"hcpost_id":102,"created":"2019-04-07T09:28:34+0000","modified":"2019-04-07T09:28:34+0000","user":{"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png"}},{"id":127,"user_id":59,"hcpost_id":102,"created":"2019-04-16T11:49:24+0000","modified":"2019-04-16T11:49:24+0000","user":{"id":59,"username":"52","photo":null}},{"id":129,"user_id":1,"hcpost_id":102,"created":"2019-04-16T14:59:01+0000","modified":"2019-04-16T14:59:01+0000","user":{"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png"}},{"id":130,"user_id":57,"hcpost_id":102,"created":"2019-04-16T15:03:36+0000","modified":"2019-04-16T15:03:36+0000","user":{"id":57,"username":"ali","photo":""}}]
         */

        private int total_like;
        private int id;
        private int healthcare_id;
        private String description;
        private String created;
        private String modified;
        private HealthcareBean healthcare;
        private List<TotalpostrepliesBean> totalpostreplies;
        private List<PostrepliesBean> postreplies;
        private List<HcphotosBean> hcphotos;
        private List<UserlikesBean> userlikes;
        private List<PostlikesBean> postlikes;

        public int getTotal_like() {
            return total_like;
        }

        public void setTotal_like(int total_like) {
            this.total_like = total_like;
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

        public HealthcareBean getHealthcare() {
            return healthcare;
        }

        public void setHealthcare(HealthcareBean healthcare) {
            this.healthcare = healthcare;
        }

        public List<TotalpostrepliesBean> getTotalpostreplies() {
            return totalpostreplies;
        }

        public void setTotalpostreplies(List<TotalpostrepliesBean> totalpostreplies) {
            this.totalpostreplies = totalpostreplies;
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

        public List<UserlikesBean> getUserlikes() {
            return userlikes;
        }

        public void setUserlikes(List<UserlikesBean> userlikes) {
            this.userlikes = userlikes;
        }

        public List<PostlikesBean> getPostlikes() {
            return postlikes;
        }

        public void setPostlikes(List<PostlikesBean> postlikes) {
            this.postlikes = postlikes;
        }

        public static class HealthcareBean {
            public String getCoverphoto() {
                return coverphoto;
            }

            public void setCoverphoto(String coverphoto) {
                this.coverphoto = coverphoto;
            }

            /**
             * id : 1
             * name : مستشفي السلام الدولي
             * mainphoto : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
             * created : 2019-03-12T14:17:43+0000
             * phone1 : hsh
             * phone2 : 01100925778
             * phone3 : 01151355233
             * address : مستشفى السلام الدولي، Port Said Street, Bneid Al Qar, Kuwait
             * website : lalalal
             * description : اكتب الوصف  هنا
             * lat : 29.3696209
             * lon : 48.0077714
             * hccare_evaluations : [{"id":1,"user_id":1,"healthcare_id":1,"sum":74,"count":30}]
             * hcworkingdays : [{"id":7,"healthcare_id":1,"dayname":"Tuesday","workingfrom":"2019-04-16T10:32:19+0000","workingto":"2019-04-16T15:32:19+0000"}]
             * favourites : []
             */

            private int id;
            private String name;
            private String mainphoto;
            private String coverphoto;
            private String created;
            private String phone1;
            private String phone2;
            private String phone3;
            private String address;
            private String website;
            private String description;
            private double lat;
            private double lon;
            private List<HccareEvaluationsBean> hccare_evaluations;
            private List<HcworkingdaysBean> hcworkingdays;
            private List<?> favourites;

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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getPhone1() {
                return phone1;
            }

            public void setPhone1(String phone1) {
                this.phone1 = phone1;
            }

            public String getPhone2() {
                return phone2;
            }

            public void setPhone2(String phone2) {
                this.phone2 = phone2;
            }

            public String getPhone3() {
                return phone3;
            }

            public void setPhone3(String phone3) {
                this.phone3 = phone3;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public List<HccareEvaluationsBean> getHccare_evaluations() {
                return hccare_evaluations;
            }

            public void setHccare_evaluations(List<HccareEvaluationsBean> hccare_evaluations) {
                this.hccare_evaluations = hccare_evaluations;
            }

            public List<HcworkingdaysBean> getHcworkingdays() {
                return hcworkingdays;
            }

            public void setHcworkingdays(List<HcworkingdaysBean> hcworkingdays) {
                this.hcworkingdays = hcworkingdays;
            }

            public List<?> getFavourites() {
                return favourites;
            }

            public void setFavourites(List<?> favourites) {
                this.favourites = favourites;
            }

            public static class HccareEvaluationsBean {
                /**
                 * id : 1
                 * user_id : 1
                 * healthcare_id : 1
                 * sum : 74
                 * count : 30
                 */

                private int id;
                private int user_id;
                private int healthcare_id;
                private int sum;
                private int count;

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

                public int getHealthcare_id() {
                    return healthcare_id;
                }

                public void setHealthcare_id(int healthcare_id) {
                    this.healthcare_id = healthcare_id;
                }

                public int getSum() {
                    return sum;
                }

                public void setSum(int sum) {
                    this.sum = sum;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }

            public static class HcworkingdaysBean {
                /**
                 * id : 7
                 * healthcare_id : 1
                 * dayname : Tuesday
                 * workingfrom : 2019-04-16T10:32:19+0000
                 * workingto : 2019-04-16T15:32:19+0000
                 */

                private int id;
                private int healthcare_id;
                private String dayname;
                private String workingfrom;
                private String workingto;

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
            }
        }

        public static class TotalpostrepliesBean {
            /**
             * hcpost_id : 102
             * total_comment : 1
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

        public static class PostrepliesBean {
            /**
             * id : 98
             * user_id : 1
             * reply : سوسو
             * hcpost_id : 102
             * created : 2019-04-16T14:59:51+0000
             * modified : 2019-04-16T14:59:51+0000
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

            public static class UserBean {
                /**
                 * id : 1
                 * username : admin
                 * photo : http://marakez.codesroots.com/webroot/img/15390008811521779367.png
                 */

                private int id;
                private String username;
                private String photo;

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
            }
        }

        public static class HcphotosBean {
            /**
             * id : 129
             * healthcare_id : 1
             * hcpost_id : 102
             * photo : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
             * created : 2019-04-16T14:58:31+0000
             * modified : 2019-04-16T14:58:31+0000
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

        public static class UserlikesBean {
            /**
             * id : 130
             * user_id : 57
             * hcpost_id : 102
             * created : 2019-04-16T15:03:36+0000
             * modified : 2019-04-16T15:03:36+0000
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

        public static class PostlikesBean {
            /**
             * id : 115
             * user_id : 1
             * hcpost_id : 102
             * created : 2019-04-07T09:28:34+0000
             * modified : 2019-04-07T09:28:34+0000
             * user : {"id":1,"username":"admin","photo":"http://marakez.codesroots.com/webroot/img/15390008811521779367.png"}
             */

            private int id;
            private int user_id;
            private int hcpost_id;
            private String created;
            private String modified;
            private UserBeanX user;

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

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public static class UserBeanX {
                /**
                 * id : 1
                 * username : admin
                 * photo : http://marakez.codesroots.com/webroot/img/15390008811521779367.png
                 */

                private int id;
                private String username;
                private String photo;

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
            }
        }
    }
}
