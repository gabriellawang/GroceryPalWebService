/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JinYuan on 5/10/2016.
 */
public class Deal {

    private int dealId;
    private String name;
    private String brand;
    private double price;
    private String description;
    private String apiKeyword;
    private String imgURL;
    private String shop;
    private String location;
    private Date dateCreated;
    private String userDeviceId;
    private int likeCount;
    private int dislikeCount;
    private int isVoted; // -1 means the current user never vote the deal.

    public Deal(int dealId, String name, String brand, double price, String description,
            String apiKeyword, String imgURL, String shop, String location,
            Date dateCreated, String userDeviceId, int likeCount, int dislikeCount) {
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
        this.isVoted = -1;
    }
    
    public Deal(int dealId, String name, String brand, double price, String description,
            String apiKeyword, String imgURL, String shop, String location,
            String dateCreated, String userDeviceId, int likeCount, int dislikeCount) {
        this.dealId = dealId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.apiKeyword = apiKeyword;
        this.imgURL = imgURL;
        this.shop = shop;
        this.location = location;
        this.userDeviceId = userDeviceId;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        try {
            this.dateCreated = format.parse(dateCreated);
        } catch (ParseException ex) {
            Logger.getLogger(Deal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isVoted = -1;
    }

    public Deal(int dealId, String name, String brand, double price, String description,
            String apiKeyword, String imgURL, String shop, String location, String userDeviceId,
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
        this.isVoted = -1;
    }

    public Deal(int dealId, String name, String brand, double price, String description, String apiKeyword, String imgURL, String shop, String location, String dateCreated, String userDeviceId, int likeCount, int dislikeCount, int isVoted) {
        this.dealId = dealId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.apiKeyword = apiKeyword;
        this.imgURL = imgURL;
        this.shop = shop;
        this.location = location;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        try {
            this.dateCreated = format.parse(dateCreated);
        } catch (ParseException ex) {
            Logger.getLogger(Deal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.userDeviceId = userDeviceId;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isVoted = isVoted;
    }
    

    public Deal(int dealId, String pName, String bName, double price, String shop, String location, String time, String imgDir, int like, int dislike, String deviceId, String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getDealId() {
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

    public String getUserDeviceId() {
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

    public int getIsVoted() {
        return isVoted;
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
