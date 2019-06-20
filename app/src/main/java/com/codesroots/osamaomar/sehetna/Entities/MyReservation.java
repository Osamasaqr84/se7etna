package com.codesroots.osamaomar.sehetna.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyReservation {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class     DataBean {
        /**
         * id : 57
         * user_id : 57
         * healthcare_id : 38
         * reservation_date : 123456
         * created : 2019-04-16T09:45:17+0000
         * modified : 2019-04-16T09:45:17+0000
         * healthcare : {"id":38,"healthcaretype_id":1,"hcsubtype_id":null,"user_id":37,"name":"مستشفي نصر الاسلام","coverphoto":"http://marakez.codesroots.com/img/no.png","mainphoto":"http://marakez.codesroots.com/img/no.png","lat":0,"lon":0,"phone1":"dddd","phone2":"","phone3":"","confirmed":false,"confirmedby":null,"website":"اكتب الموقع الاليكتروني  هنا","address":"","247days":false,"created":"2019-03-25T09:44:48+0000","modified":"2019-03-25T13:45:05+0000","description":"اكتب الوصف  هنا","specialization":null}
         */

        private int id;
        private int user_id;
        private int healthcare_id;
        private String reservation_date;
        private String created;
        private String modified;
        private HealthcareBean healthcare;

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

        public String getReservation_date() {
            return reservation_date;
        }

        public void setReservation_date(String reservation_date) {
            this.reservation_date = reservation_date;
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
             * id : 38
             * healthcaretype_id : 1
             * hcsubtype_id : null
             * user_id : 37
             * name : مستشفي نصر الاسلام
             * coverphoto : http://marakez.codesroots.com/img/no.png
             * mainphoto : http://marakez.codesroots.com/img/no.png
             * lat : 0
             * lon : 0
             * phone1 : dddd
             * phone2 :
             * phone3 :
             * confirmed : false
             * confirmedby : null
             * website : اكتب الموقع الاليكتروني  هنا
             * address :
             * 247days : false
             * created : 2019-03-25T09:44:48+0000
             * modified : 2019-03-25T13:45:05+0000
             * description : اكتب الوصف  هنا
             * specialization : null
             */

            private int id;
            private int healthcaretype_id;
            private Object hcsubtype_id;
            private int user_id;
            private String name;
            private String coverphoto;
            private String mainphoto;
            private float lat;
            private float lon;
            private String phone1;
            private String phone2;
            private String phone3;
            private boolean confirmed;
            private String confirmedby;
            private String website;
            private String address;
            @SerializedName("247days")
            private boolean _$247days;
            private String created;
            private String modified;
            private String description;
            private Object specialization;

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

            public float getLat() {
                return lat;
            }

            public void setLat(int lat) {
                this.lat = lat;
            }

            public float getLon() {
                return lon;
            }

            public void setLon(int lon) {
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

            public Object getConfirmedby() {
                return confirmedby;
            }

            public void setConfirmedby(String confirmedby) {
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getSpecialization() {
                return specialization;
            }

            public void setSpecialization(Object specialization) {
                this.specialization = specialization;
            }
        }
    }
}
