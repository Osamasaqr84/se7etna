package com.codesroots.osamaomar.sehetna.Entities;

import java.util.List;

public class Categories {


    private List<Types> types;
    /**
     * success : true
     */

    private boolean success;

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class Types {
        /**
         * id : 1
         * name : مستشفيات
         * icon : Group 11.png
         * type : hospital
         * hcsubtypes : [{"id":1,"name":"مستشفي الجراحة","icon":"سشيسشي","healthcaretype_id":1}]
         */

        private int id;
        private String name;
        private String icon;
        private String type;
        private List<Hcsubtypes> hcsubtypes;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Hcsubtypes> getHcsubtypes() {
            return hcsubtypes;
        }

        public void setHcsubtypes(List<Hcsubtypes> hcsubtypes) {
            this.hcsubtypes = hcsubtypes;
        }

        public static class Hcsubtypes {
            /**
             * id : 1
             * name : مستشفي الجراحة
             * icon : سشيسشي
             * healthcaretype_id : 1
             */

            private int id;
            private String name;
            private String icon;
            private int healthcaretype_id;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getHealthcaretype_id() {
                return healthcaretype_id;
            }

            public void setHealthcaretype_id(int healthcaretype_id) {
                this.healthcaretype_id = healthcaretype_id;
            }
        }
    }




}
