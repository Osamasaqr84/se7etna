package com.codesroots.osamaomar.sehetna.Entities;

public class AddLike {


    /**
     * postlike : {"user_id":62,"hcpost_id":127,"created":"2019-04-17T14:04:17+0000","modified":"2019-04-17T14:04:17+0000","id":145}
     * likeid : 145
     */

    private PostlikeBean postlike;
    private int likeid;

    public PostlikeBean getPostlike() {
        return postlike;
    }

    public void setPostlike(PostlikeBean postlike) {
        this.postlike = postlike;
    }

    public int getLikeid() {
        return likeid;
    }

    public void setLikeid(int likeid) {
        this.likeid = likeid;
    }

    public static class PostlikeBean {
        /**
         * user_id : 62
         * hcpost_id : 127
         * created : 2019-04-17T14:04:17+0000
         * modified : 2019-04-17T14:04:17+0000
         * id : 145
         */

        private int user_id;
        private int hcpost_id;
        private String created;
        private String modified;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
