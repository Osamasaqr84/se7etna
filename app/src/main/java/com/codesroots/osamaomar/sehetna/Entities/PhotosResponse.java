package com.codesroots.osamaomar.sehetna.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PhotosResponse {


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
         * name : مستشفي السلام
         * coverphoto : http://marakez.codesroots.com/library/coverphoto/15505043891979262832.png
         * hcphotos : [{"id":1,"healthcare_id":1,"hcpost_id":11,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:25:42+0000","modified":"2019-03-04T12:25:42+0000"},{"id":2,"healthcare_id":1,"hcpost_id":10,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:26:18+0000","modified":"2019-03-04T12:26:18+0000"},{"id":3,"healthcare_id":1,"hcpost_id":10,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:26:28+0000","modified":"2019-03-04T12:26:28+0000"},{"id":4,"healthcare_id":1,"hcpost_id":9,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:27:31+0000","modified":"2019-03-04T12:27:31+0000"},{"id":5,"healthcare_id":1,"hcpost_id":9,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:27:31+0000","modified":"2019-03-04T12:27:31+0000"},{"id":6,"healthcare_id":1,"hcpost_id":9,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:27:31+0000","modified":"2019-03-04T12:27:31+0000"},{"id":7,"healthcare_id":1,"hcpost_id":8,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:31:06+0000","modified":"2019-03-04T12:31:06+0000"},{"id":8,"healthcare_id":1,"hcpost_id":8,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:31:07+0000","modified":"2019-03-04T12:31:07+0000"},{"id":9,"healthcare_id":1,"hcpost_id":8,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:31:07+0000","modified":"2019-03-04T12:31:07+0000"},{"id":10,"healthcare_id":1,"hcpost_id":8,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-04T12:31:07+0000","modified":"2019-03-04T12:31:07+0000"},{"id":11,"healthcare_id":1,"hcpost_id":64,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:21:50+0000","modified":"2019-03-05T14:21:50+0000"},{"id":12,"healthcare_id":1,"hcpost_id":62,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:21:51+0000","modified":"2019-03-05T14:21:51+0000"},{"id":13,"healthcare_id":1,"hcpost_id":63,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:21:51+0000","modified":"2019-03-05T14:21:51+0000"},{"id":14,"healthcare_id":1,"hcpost_id":63,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:21:51+0000","modified":"2019-03-05T14:21:51+0000"},{"id":15,"healthcare_id":1,"hcpost_id":63,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:21:52+0000","modified":"2019-03-05T14:21:52+0000"},{"id":16,"healthcare_id":1,"hcpost_id":63,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:30:15+0000","modified":"2019-03-05T14:30:15+0000"},{"id":17,"healthcare_id":1,"hcpost_id":62,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:30:40+0000","modified":"2019-03-05T14:30:40+0000"},{"id":18,"healthcare_id":1,"hcpost_id":62,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:30:40+0000","modified":"2019-03-05T14:30:40+0000"},{"id":19,"healthcare_id":1,"hcpost_id":64,"photo":"http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg","created":"2019-03-05T14:31:04+0000","modified":"2019-03-05T14:31:04+0000"}]
         */

        private int id;
        private String name;
        private String coverphoto;
        private List<HcphotosBean> hcphotos;

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

        public String getCoverphoto() {
            return coverphoto;
        }

        public void setCoverphoto(String coverphoto) {
            this.coverphoto = coverphoto;
        }

        public List<HcphotosBean> getHcphotos() {
            return hcphotos;
        }

        public void setHcphotos(List<HcphotosBean> hcphotos) {
            this.hcphotos = hcphotos;
        }

        public static class HcphotosBean implements Parcelable {
            public HcphotosBean(int id, String photo) {
                this.id = id;
                this.photo = photo;
            }

            /**
             * id : 1
             * healthcare_id : 1
             * hcpost_id : 11
             * photo : http://marakez.codesroots.com/img/2018_1_18_9_22_48_428.jpg
             * created : 2019-03-04T12:25:42+0000
             * modified : 2019-03-04T12:25:42+0000
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        }
    }
}
