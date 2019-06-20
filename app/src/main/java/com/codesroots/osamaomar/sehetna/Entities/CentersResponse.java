package com.codesroots.osamaomar.sehetna.Entities;

import java.util.List;

public class CentersResponse {

    private List<PostsBean> posts;

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

        public static class PostsBean {
        /**
         * id : 1
         * healthcaretype_id : 1
         * user_id : 1
         * name : مستشفي السلام
         * coverphoto : http://marakez.codesroots.com/library/coverphoto/15505043891979262832.png
         * mainphoto : http://marakez.codesroots.com/library/mainphoto/15505043891671404486.png
         * lat : 29.96623
         * lon : 31.24567
         * phone1 : 011
         * phone2 : 010
         * phone3 : 012
         * address : كورنيش المعادي
         * website : http://www.assih.com
         * description : وريم ايبسوم دولار سيت أميت ,كونسيكتيتور أدايبا يسكينج أليايت,سيت دو أيوسمود تيمبور أنكايديديونتيوت لابوري ات دولار ماجنا أليكيوا . يوت انيم أد مينيم فينايم,كيواس نوستريد أكسير سيتاشن يللأمكو لابورأس نيسي يت أليكيوب أكس أيا كوممودو كونسيكيوات . ديواس أيوتي أريري دولار إن ريبريهينديرأيت فوليوبتاتي فيلايت أيسسي
         * specialization : مستشفي الجراحة
         * hcworkingdays : [{"id":3,"healthcare_id":1,"dayname":"Monday","workingfrom":"2019-03-18T17:00:34+0000","workingto":"2019-03-18T17:00:34+0000"}]
         * hccare_evaluations : [{"id":1,"user_id":1,"healthcare_id":1,"sum":67,"count":24}]
         * favourites : [{"id":110,"healthcare_id":1,"user_id":1}]
         */

        private int id;
        private int healthcaretype_id;
        private int user_id;
        private String name;
        private String coverphoto;
        private String mainphoto;
        private double lat;
        private double lon;
        private String phone1;
        private String phone2;
        private String phone3;
        private String address;
        private String website;
        private String description;
        private String specialization;
        private List<HcworkingdaysBean> hcworkingdays;
        private List<HccareEvaluationsBean> hccare_evaluations;
        private List<FavouritesBean> favourites;

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

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public List<HcworkingdaysBean> getHcworkingdays() {
            return hcworkingdays;
        }

        public void setHcworkingdays(List<HcworkingdaysBean> hcworkingdays) {
            this.hcworkingdays = hcworkingdays;
        }

        public List<HccareEvaluationsBean> getHccare_evaluations() {
            return hccare_evaluations;
        }

        public void setHccare_evaluations(List<HccareEvaluationsBean> hccare_evaluations) {
            this.hccare_evaluations = hccare_evaluations;
        }

        public List<FavouritesBean> getFavourites() {
            return favourites;
        }

        public void setFavourites(List<FavouritesBean> favourites) {
            this.favourites = favourites;
        }

        public static class HcworkingdaysBean {
            /**
             * id : 3
             * healthcare_id : 1
             * dayname : Monday
             * workingfrom : 2019-03-18T17:00:34+0000
             * workingto : 2019-03-18T17:00:34+0000
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

        public static class HccareEvaluationsBean {
            /**
             * id : 1
             * user_id : 1
             * healthcare_id : 1
             * sum : 67
             * count : 24
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

        public static class FavouritesBean {
            /**
             * id : 110
             * healthcare_id : 1
             * user_id : 1
             */

            private int id;
            private int healthcare_id;
            private int user_id;

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
        }
    }
}
