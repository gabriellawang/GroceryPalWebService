/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by JinYuan on 5/10/2016.
 */
public class Deal {

    private String dealId;
    private String name;
    private String brand;
    private double price;
    private String description;
    private String apiKeyword;
    private String imgURL;
    private String shop;
    private String location;
    private Date dateCreated;
    private int userDeviceId;
    private int likeCount;
    private int dislikeCount;

    public Deal(String dealId, String name, String brand, double price, String description,
            String apiKeyword, String imgURL, String shop, String location,
            Date dateCreated, int userDeviceId, int likeCount, int dislikeCount) {
        this.dealId = dealId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.apiKeyword = apiKeyword;
        this.imgURL = imgURL;
        this.shop = shop;
        this.location = location;
        this.dateCreated = dateCreated;
        this.userDeviceId = userDeviceId;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public Deal(String dealId, String name, String brand, double price, String description,
            String apiKeyword, String imgURL, String shop, String location, int userDeviceId,
            int likeCount, int dislikeCount) {
        this.dealId = dealId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.apiKeyword = apiKeyword;
        this.imgURL = imgURL;
        this.shop = shop;
        this.location = location;
        this.dateCreated = new Date();
        this.userDeviceId = userDeviceId;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public String getDealId() {
        return dealId;
    }

    public String getBrand() {
        return brand;
    }

    public String getApiKeyword() {
        return apiKeyword;
    }

    public String getLocation() {
        return location;
    }

    public int getUserDeviceId() {
        return userDeviceId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getShop() {
        return shop;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return format.format(dateCreated);
    }
}
