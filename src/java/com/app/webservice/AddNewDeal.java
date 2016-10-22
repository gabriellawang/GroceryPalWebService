/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.webservice;

import com.app.DAO.DealDAO;
import com.app.model.Deal;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author gabriellawang
 */
public class AddNewDeal extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            ServletContext context = request.getServletContext();
            File repository = (File) context.getAttribute(ServletContext.TEMPDIR);

            HashMap<String, String> map = retrieveFile(repository.getAbsolutePath(), request);

            String name = map.get("name");
            String shop = map.get("shop");
            String brand = map.get("brand");
            double price = Double.parseDouble(map.get("price"));
            String description = map.get("description");
            String deviceId = map.get("udid");
            String imgURL = "";
            if (map.get("filename") != null) {
                
                imgURL = "http://"+request.getServerName()+":"+request.getServerPort()+context.getContextPath()+"/image?name=" + map.get("filename");
                System.out.println("imgURL = " + imgURL);
            }

            /*
             Hey Ivan:
             Before adding the new deal into database, we need to call the location service to 
             get the current location of the shop.
             We also need to send the image to vision API to collect the keywords for future searching.
             Right now we just hard code these two value.
             */

            Deal deal = new Deal(-1, name, brand, price, description, "Google Vision API",
                    imgURL, shop, "location data", deviceId, 0, 0);
            DealDAO dealDao = new DealDAO();
            dealDao.addDeal(deal);
            dealDao.closeConnection();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static HashMap<String, String> retrieveFile(String folder, HttpServletRequest request) throws IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        HashMap<String, String> toReturn = new HashMap<String, String>();
        File repository = new File(folder);
        factory.setRepository(repository);

        String filePath = repository.getAbsolutePath();
        ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);

        List<FileItem> list;
        try {
            // Parse the request
            list = upload.parseRequest(request);
            for (FileItem f : list) {
                if (!f.isFormField()) {
                    if (f.getName().contains(".")) {
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSSS");
                        String filename = sdf.format(d) + ".jpg";
                        f.write(new File(filePath + File.separator + filename));
                        toReturn.put("filename", filename);
                    }
                } else {
                    toReturn.put(f.getFieldName(), f.getString());
                }

            }
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return toReturn;
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
