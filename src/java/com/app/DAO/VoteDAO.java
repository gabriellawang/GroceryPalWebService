/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.DAO;

import com.app.model.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriellawang
 */
public class VoteDAO {

    /*
     public static void main(String[] args) throws SQLException {
     VoteDAO v = new VoteDAO();
     v.voteDeal(1, "7e1907ef5fffa266", 1);
     v.closeConnection();
     }
     */
    public static void voteDeal(int dealId, String deviceId, int isLiked) {
        //add the vote inside grocerypal.vote
        //update the grocerypal.deal
        //isLiked = 0 means dislike; isLiked = 1 means like
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `grocerypal`.`vote`"
                    + "(`device_id`,`is_like`,`deal_id`) values(?,?,?);");
            stmt.setString(1, deviceId);
            stmt.setInt(2, isLiked);
            stmt.setInt(3, dealId);
            stmt.execute();

            stmt = conn.prepareStatement("update grocerypal.deal set deal.like_count="
                    + "(select count(*) from grocerypal.vote where vote.deal_id=? and vote.is_like=1), "
                    + "deal.dislike_count=(select count(*) from grocerypal.vote where vote.deal_id=? and vote.is_like=0)"
                    + "where deal_id=?;");
            stmt.setInt(1, dealId);
            stmt.setInt(2, dealId);
            stmt.setInt(3, dealId);
            stmt.execute();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DealDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
