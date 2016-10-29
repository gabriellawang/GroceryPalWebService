/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.DAO;

import com.app.model.ConnectionManager;
import com.app.model.Deal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriellawang
 */
public class DealDAO {

    public static void addDeal(Deal newDeal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `grocerypal`.`deal` "
                    + "(`product_name`,`brand_name`,`price`,`shop`,`location`,`time`,`img_dir`,`like_count`,`dislike_count`,`device_id`,`api_keyword`,`description`) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, newDeal.getName());
            stmt.setString(2, newDeal.getBrand());
            stmt.setDouble(3, newDeal.getPrice());
            stmt.setString(4, newDeal.getShop());
            stmt.setString(5, newDeal.getLocation());
            stmt.setString(6, newDeal.getDateString());
            stmt.setString(7, newDeal.getImgURL());
            stmt.setInt(8, newDeal.getLikeCount());
            stmt.setInt(9, newDeal.getDislikeCount());
            stmt.setString(10, newDeal.getUserDeviceId());
            stmt.setString(11, newDeal.getApiKeyword());
            stmt.setString(12, newDeal.getDescription());

            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateLikes() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int size = 0;
        try {
            //conn.setAutoCommit(false);
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("select count(*) from grocerypal.deal;");
            rs = stmt.executeQuery();
            while (rs.next()) {
                size = rs.getInt(1);
            }

            for (int i = 1; i <= size; i++) {
                stmt = conn.prepareStatement("update grocerypal.deal set deal.like_count="
                        + "(select count(*) from grocerypal.vote where vote.deal_id=? and vote.is_like=1), "
                        + "deal.dislike_count=(select count(*) from grocerypal.vote where vote.deal_id=? and vote.is_like=0)"
                        + "where deal_id=?;");
                stmt.setInt(1, i);
                stmt.setInt(2, i);
                stmt.setInt(3, i);
                stmt.execute();
            }
            //conn.commit();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Deal> retrieveDeals(String udid, int range, int row) {
        Connection conn = null;
        ArrayList<Deal> dList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            dList = new ArrayList<>();

            stmt = conn.prepareStatement("select deal.*, vote.is_like from grocerypal.deal "
                    + "left join grocerypal.vote on vote.device_id=? "
                    + "and deal.deal_id=vote.deal_id "
                    + "order by deal_id DESC limit ?,?;");
            stmt.setString(1, udid);
            stmt.setInt(2, row);
            stmt.setInt(3, range);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int dealId = rs.getInt(1);
                String pName = rs.getString(2);
                String bName = rs.getString(3);
                double price = rs.getDouble(4);
                String shop = rs.getString(5);
                String location = rs.getString(6);
                String time = rs.getString(7);
                String imgDir = rs.getString(8);
                int like = rs.getInt(9);
                int dislike = rs.getInt(10);
                String deviceId = rs.getString(11);
                String keyword = rs.getString(12);
                String description = rs.getString(13);
                int isLiked = rs.getInt(14);
                if (rs.wasNull()) {
                    isLiked = -1;
                }
                Deal d = new Deal(dealId, pName, bName, price, description, keyword,
                        imgDir, shop, location, time, deviceId, like, dislike, isLiked);
                dList.add(d);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dList;
    }

    //This method allow user to search part product property(labels) to get Deal array that contains the partial product property
    public static ArrayList<Deal> retrieveDealsByProperty(String udid, String property) {
        Connection conn = null;
        ArrayList<Deal> dList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            dList = new ArrayList<>();

            stmt = conn.prepareStatement("SELECT deal.*, vote.is_like FROM grocerypal.deal "
                    + "left join grocerypal.vote on vote.device_id=? and deal.deal_id=vote.deal_id "
                    + "WHERE deal.api_keyword LIKE ? ");
            stmt.setString(1, udid);
            stmt.setString(2, "%" + property + "%");

            rs = stmt.executeQuery();
            while (rs.next()) {
                int dealId = rs.getInt(1);
                String pName = rs.getString(2);
                String bName = rs.getString(3);
                double price = rs.getDouble(4);
                String shop = rs.getString(5);
                String location = rs.getString(6);
                String time = rs.getString(7);
                String imgDir = rs.getString(8);
                int like = rs.getInt(9);
                int dislike = rs.getInt(10);
                String deviceId = rs.getString(11);
                String keyword = rs.getString(12);
                String description = rs.getString(13);
                int isLiked = rs.getInt(14);
                if (rs.wasNull()) {
                    isLiked = -1;
                }
                Deal d = new Deal(dealId, pName, bName, price, description, keyword,
                        imgDir, shop, location, time, deviceId, like, dislike, isLiked);
                dList.add(d);
            }
            //conn.commit();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dList;
    }

    //This method allow user to search part product name to get Deal array that contains the partial product name
    public static ArrayList<Deal> retrieveDealsByNameElement(String udid, String nameElement) {
        Connection conn = null;
        ArrayList<Deal> dList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            dList = new ArrayList<>();
            nameElement = "%" + nameElement + "%";

            stmt = conn.prepareStatement("SELECT deal.*, vote.is_like FROM grocerypal.deal "
                    + "left join grocerypal.vote on vote.device_id=? and deal.deal_id=vote.deal_id "
                    + "WHERE product_name LIKE ? or brand_name LIKE ?");
            
            stmt.setString(1, udid);
            stmt.setString(2, nameElement);
            stmt.setString(3, nameElement);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int dealId = rs.getInt(1);
                String pName = rs.getString(2);
                String bName = rs.getString(3);
                double price = rs.getDouble(4);
                String shop = rs.getString(5);
                String location = rs.getString(6);
                String time = rs.getString(7);
                String imgDir = rs.getString(8);
                int like = rs.getInt(9);
                int dislike = rs.getInt(10);
                String deviceId = rs.getString(11);
                String keyword = rs.getString(12);
                String description = rs.getString(13);
                int isLiked = rs.getInt(14);
                if (rs.wasNull()) {
                    isLiked = -1;
                }
                Deal d = new Deal(dealId, pName, bName, price, description, keyword,
                        imgDir, shop, location, time, deviceId, like, dislike, isLiked);
                dList.add(d);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dList;
    }

    /*
    public static Deal retrieveDealsById(int id) {
        Connection conn = null;
        ArrayList<Deal> dList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Deal d = null;
        try {
            conn = ConnectionManager.getConnection();
            dList = new ArrayList<>();
            //conn.setAutoCommit(false);
            //I think the SQL statement is wrong.  PLS make it correct. 
            stmt = conn.prepareStatement("SELECT * FROM deal WHERE deal_id = ? ");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int dealId = rs.getInt(1);
                String pName = rs.getString(2);
                String bName = rs.getString(3);
                double price = rs.getDouble(4);
                String shop = rs.getString(5);
                String location = rs.getString(6);
                String time = rs.getString(7);
                String imgDir = rs.getString(8);
                int like = rs.getInt(9);
                int dislike = rs.getInt(10);
                String deviceId = rs.getString(11);
                String keyword = rs.getString(12);
                String description = rs.getString(13);
                int isLiked = rs.getInt(14);
                if (rs.wasNull()) {
                    isLiked = -1;
                }
                d = new Deal(dealId, pName, bName, price, description, keyword,
                        imgDir, shop, location, time, deviceId, like, dislike, isLiked);

            }
            conn.commit();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    */
}
