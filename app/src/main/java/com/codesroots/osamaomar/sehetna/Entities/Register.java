package com.codesroots.osamaomar.sehetna.Entities;

public class Register {

    /**
     * success : true
     * data : {"id":56,"usergroupid":1,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjU2LCJleHAiOjE1NTU4NTkwOTV9.f5Qs7k8ZUjppDEhf6oScOQO5lw7ynVOKHH4mz1iCwcg"}
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
         * id : 56
         * usergroupid : 1
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjU2LCJleHAiOjE1NTU4NTkwOTV9.f5Qs7k8ZUjppDEhf6oScOQO5lw7ynVOKHH4mz1iCwcg
         */

        private int id;
        private int usergroupid;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUsergroupid() {
            return usergroupid;
        }

        public void setUsergroupid(int usergroupid) {
            this.usergroupid = usergroupid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}


