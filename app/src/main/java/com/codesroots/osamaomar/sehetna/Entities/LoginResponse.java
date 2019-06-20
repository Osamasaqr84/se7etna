package com.codesroots.osamaomar.sehetna.Entities;

public class LoginResponse {

    /**
     * success : true
     * data : {"userid":null,"username":null,"groupid":null,"healthcare_id":0,"photo":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOm51bGwsImV4cCI6MTU2MTUwMDE1Mn0.5UItnTCZDTuT3XxLZXUf4ZD1N-0wiQQNOV2pEPWbgBw"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : null
         * username : null
         * groupid : null
         * healthcare_id : 0
         * photo : null
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOm51bGwsImV4cCI6MTU2MTUwMDE1Mn0.5UItnTCZDTuT3XxLZXUf4ZD1N-0wiQQNOV2pEPWbgBw
         */

        private int userid;
        private String username;
        private int groupid;
        private int healthcare_id;
        private String photo;
        private String token;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public int getHealthcare_id() {
            return healthcare_id;
        }

        public void setHealthcare_id(int healthcare_id) {
            this.healthcare_id = healthcare_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
