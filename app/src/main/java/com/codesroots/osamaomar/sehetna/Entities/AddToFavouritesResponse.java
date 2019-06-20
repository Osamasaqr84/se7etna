package com.codesroots.osamaomar.sehetna.Entities;

public class AddToFavouritesResponse {


    /**
     * success : true
     * idfav : 116
     */

    private boolean success;
    private int idfav;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getIdfav() {
        return idfav;
    }

    public void setIdfav(int idfav) {
        this.idfav = idfav;
    }
}
