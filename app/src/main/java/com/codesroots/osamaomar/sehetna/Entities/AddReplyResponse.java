package com.codesroots.osamaomar.sehetna.Entities;

import java.util.List;

public class AddReplyResponse {


    private List<PostreplyBean> postreply;

    public List<PostreplyBean> getPostreply() {
        return postreply;
    }

    public void setPostreply(List<PostreplyBean> postreply) {
        this.postreply = postreply;
    }

    public static class PostreplyBean {
        /**
         * id : 147
         * user_id : 62
         * reply : رد
         * hcpost_id : 130
         * created : 2019-04-18T21:52:25+0000
         * modified : 2019-04-18T21:52:25+0000
         * user : {"username":"اسامه الصقر ","phone":"01201089811"}
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
             * username : اسامه الصقر
             * phone : 01201089811
             */

            private String username;
            private String phone;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
