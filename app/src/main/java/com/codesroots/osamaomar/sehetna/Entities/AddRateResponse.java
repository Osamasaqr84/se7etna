package com.codesroots.osamaomar.sehetna.Entities;

public class AddRateResponse {


    /**
     * hccareEvaluation : {"healthcare_id":1,"user_id":1,"rate":4,"comment":"\"kljkjhh\"","created":"2019-03-11T09:19:28+0000","modified":"2019-03-11T09:19:28+0000","id":11}
     */

    private HccareEvaluationBean hccareEvaluation;

    public HccareEvaluationBean getHccareEvaluation() {
        return hccareEvaluation;
    }

    public void setHccareEvaluation(HccareEvaluationBean hccareEvaluation) {
        this.hccareEvaluation = hccareEvaluation;
    }

    public static class HccareEvaluationBean {
        /**
         * healthcare_id : 1
         * user_id : 1
         * rate : 4
         * comment : "kljkjhh"
         * created : 2019-03-11T09:19:28+0000
         * modified : 2019-03-11T09:19:28+0000
         * id : 11
         */

        private int healthcare_id;
        private int user_id;
        private int rate;
        private String comment;
        private String created;
        private String modified;
        private int id;

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

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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
