package com.codesroots.osamaomar.sehetna.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavouriteResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * healthcare_id : 1
         * user_id : 1
         * created : 2019-02-18T14:52:29+0000
         * modified : 2019-02-18T14:52:29+0000
         * healthcare : {"id":1,"healthcaretype_id":1,"hcsubtype_id":null,"user_id":1,"name":"مستشفي السلام","coverphoto":"http://marakez.codesroots.com/library/coverphoto/15505043891979262832.png","mainphoto":"http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png","lat":29.12345,"lon":30.12345,"phone1":"011","phone2":"010","phone3":"012","confirmed":false,"confirmedby":1,"website":"","address":"","247days":false,"created":null,"modified":"2019-02-18T15:39:49+0000","hccare_evaluations":[{"id":1,"user_id":1,"healthcare_id":1,"sum":9,"count":4}],"hcworkingdays":[{"id":4,"healthcare_id":1,"dayname":"Thursday","workingfrom":"2019-03-07T09:15:41+0000","workingto":"2019-03-07T16:15:41+0000"}],"healthcaretype":{"name":"مستشفيات"}}
         */

        private int id;
        private int healthcare_id;
        private int user_id;
        private String created;
        private String modified;
        private HealthcareBean healthcare;

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

        public static class HealthcareBean {
            /**
             * id : 1
             * healthcaretype_id : 1
             * hcsubtype_id : null
             * user_id : 1
             * name : مستشفي السلام
             * coverphoto : http://marakez.codesroots.com/library/coverphoto/15505043891979262832.png
             * mainphoto : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
             * lat : 29.12345
             * lon : 30.12345
             * phone1 : 011
             * phone2 : 010
             * phone3 : 012
             * confirmed : false
             * confirmedby : 1
             * website :
             * address :
             * 247days : false
             * created : null
             * modified : 2019-02-18T15:39:49+0000
             * hccare_evaluations : [{"id":1,"user_id":1,"healthcare_id":1,"sum":9,"count":4}]
             * hcworkingdays : [{"id":4,"healthcare_id":1,"dayname":"Thursday","workingfrom":"2019-03-07T09:15:41+0000","workingto":"2019-03-07T16:15:41+0000"}]
             * healthcaretype : {"name":"مستشفيات"}
             */

            private int id;
            private int healthcaretype_id;
            private Object hcsubtype_id;
            private int user_id;
            private String name;
            private String coverphoto;
            private String mainphoto;
            private double lat;
            private double lon;
            private String phone1;
            private String phone2;
            private String phone3;
            private boolean confirmed;
            private int confirmedby;
            private String website;
            private String address;
            @SerializedName("247days")
            private boolean _$247days;
            private Object created;
            private String modified;
            private HealthcaretypeBean healthcaretype;
            private List<HccareEvaluationsBean> hccare_evaluations;
            private List<HcworkingdaysBean> hcworkingdays;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getHealthcaretype_id() {
                return healthcaretype_id;
            }

            public void setHealthcaretype_id(int healthcaretype_id) {
                this.healthcaretype_id = healthcaretype_id;
            }

            public Object getHcsubtype_id() {
                return hcsubtype_id;
            }

            public void setHcsubtype_id(Object hcsubtype_id) {
                this.hcsubtype_id = hcsubtype_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoverphoto() {
                return coverphoto;
            }

            public void setCoverphoto(String coverphoto) {
                this.coverphoto = coverphoto;
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

            public boolean isConfirmed() {
                return confirmed;
            }

            public void setConfirmed(boolean confirmed) {
                this.confirmed = confirmed;
            }

            public int getConfirmedby() {
                return confirmedby;
            }

            public void setConfirmedby(int confirmedby) {
                this.confirmedby = confirmedby;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public boolean is_$247days() {
                return _$247days;
            }

            public void set_$247days(boolean _$247days) {
                this._$247days = _$247days;
            }

            public Object getCreated() {
                return created;
            }

            public void setCreated(Object created) {
                this.created = created;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public HealthcaretypeBean getHealthcaretype() {
                return healthcaretype;
            }

            public void setHealthcaretype(HealthcaretypeBean healthcaretype) {
                this.healthcaretype = healthcaretype;
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

            public static class HealthcaretypeBean {
                /**
                 * name : مستشفيات
                 */

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class HccareEvaluationsBean {
                /**
                 * id : 1
                 * user_id : 1
                 * healthcare_id : 1
                 * sum : 9
                 * count : 4
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
                 * id : 4
                 * healthcare_id : 1
                 * dayname : Thursday
                 * workingfrom : 2019-03-07T09:15:41+0000
                 * workingto : 2019-03-07T16:15:41+0000
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
    }
}
