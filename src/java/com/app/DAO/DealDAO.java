/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.DAO;

import com.app.model.ConnectionManager;
import com.app.model.Deal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriellawang
 */
public class DealDAO {
    
    private static Connection conn;
    
    public DealDAO() throws SQLException{
        conn = ConnectionManager.getConnection();
    }
    
    public void addDeal(Deal newDeal){
        PreparedStatement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("INSERT INTO `grocerypal`.`deal` "
                    + "(`product_name`,`brand_name`,`price`,`shop`,`location`,`time`,`img_dir`,`like_count`,`dislike_count`,`device_id`,`api_keyword`) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, newDeal.getName());
            stmt.setString(2, newDeal.getBrand());
            stmt.setDouble(3, newDeal.getPrice());
            stmt.setString(4, newDeal.getShop());
            stmt.setString(5, newDeal.getLocation());
            stmt.setDate(6, (Date) newDeal.getDateCreated());
            stmt.setString(7, newDeal.getImgURL());
            stmt.setInt(8, newDeal.getLikeCount());
            stmt.setInt(9, newDeal.getDislikeCount());
            stmt.setString(10, newDeal.getUserDeviceId());
            stmt.setString(11, newDeal.getApiKeyword());
            
            stmt.execute();
            conn.commit();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
