/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.util.Date;

/**
 *
 * @author gabriellawang
 */
public class Review {

    private int reviewId;
    private int dealId;
    private String userDeviceId;
    private String content;
    private Date postTime;

    public Review(int reviewId, int dealId, String userDeviceId, String content, Date postTime) {
        this.reviewId = reviewId;
        this.dealId = dealId;
        this.userDeviceId = userDeviceId;
        this.content = content;
        this.postTime = postTime;
    }

    public Review(int reviewId, int dealId, String userDeviceId, String content) {
        this.reviewId = reviewId;
        this.dealId = dealId;
        this.userDeviceId = userDeviceId;
        this.content = content;
        this.postTime = new Date();
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getDealId() {
        return dealId;
    }

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public String getContent() {
        return content;
    }

    public Date getPostTime() {
        return postTime;
    }

}
