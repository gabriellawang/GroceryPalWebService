/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

/**
 *
 * @author gabriellawang
 */
public class Vote {

    private int voteId;
    private String userDeviceId;
    private boolean isLike;
    private int dealId;

    public Vote(int voteId, String userDeviceId, boolean isLike, int dealId) {
        this.voteId = voteId;
        this.userDeviceId = userDeviceId;
        this.isLike = isLike;
        this.dealId = dealId;
    }

    public int getVoteId() {
        return voteId;
    }

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public int getDealId() {
        return dealId;
    }

}
