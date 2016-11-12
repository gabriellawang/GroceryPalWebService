/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.webservice;

import com.app.DAO.DealDAO;
import com.app.model.Deal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriellawang
 */
@WebServlet(name = "RetrieveDeal", urlPatterns = {"/get-deals"})
public class RetrieveDeal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/JSON");
        try (PrintWriter out = response.getWriter()) {
            //ServletContext context = request.getServletContext();
            //File repository = (File) context.getAttribute(ServletContext.TEMPDIR);

            //HashMap<String, String> map = retrieveFile(repository.getAbsolutePath(), request);
            String udid = request.getParameter("udid");
            int range = Integer.parseInt(request.getParameter("range"));
            int row = Integer.parseInt(request.getParameter("row"));
            DealDAO.updateLikes();
            ArrayList<Deal> dList = DealDAO.retrieveDeals(udid, range, row);
            row += range;

            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonObject jsonOutput = new JsonObject();
            jsonOutput.addProperty("row", row);
            JsonArray dealArray = new JsonArray();
            for (int i = 0; i < dList.size(); i++) {
                Deal d = dList.get(i);
                JsonObject dObject = new JsonObject();
                dObject.addProperty("deal_id", d.getDealId());
                dObject.addProperty("product_name", d.getName());
                dObject.addProperty("brand_name", d.getBrand());
                dObject.addProperty("price", d.getPrice());
                dObject.addProperty("shop", d.getShop());
                dObject.addProperty("location", d.getLocation());
                dObject.addProperty("time", d.getDateString());
                dObject.addProperty("img_dir", d.getImgURL());
                dObject.addProperty("like_count", d.getLikeCount());
                dObject.addProperty("dislike_count", d.getDislikeCount());
                dObject.addProperty("device_id", d.getUserDeviceId());
                dObject.addProperty("api_keyword", d.getApiKeyword());
                dObject.addProperty("description", d.getDescription());
                dObject.addProperty("is_voted", d.getIsVoted());
                dealArray.add(dObject);
            }
            jsonOutput.add("deals", dealArray);

            try {
                out.println(gson.toJson(jsonOutput));
                System.out.println(gson.toJson(jsonOutput));
            } finally {
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
