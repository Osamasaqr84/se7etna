package com.codesroots.osamaomar.sehetna.Entities;

public class Reservation {


    /**
     * reservation : {"healthcare_id":38,"reservation_date":"123456","user_id":57,"created":"2019-04-16T09:45:43+0000","modified":"2019-04-16T09:45:43+0000","id":60}
     */

    private ReservationBean reservation;

    public ReservationBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationBean reservation) {
        this.reservation = reservation;
    }

    public static class ReservationBean {
        /**
         * healthcare_id : 38
         * reservation_date : 123456
         * user_id : 57
         * created : 2019-04-16T09:45:43+0000
         * modified : 2019-04-16T09:45:43+0000
         * id : 60
         */

        private int healthcare_id;
        private String reservation_date;
        private int user_id;
        private String created;
        private String modified;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
